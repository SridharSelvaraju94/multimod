
package com.project.repo.repository.specifications;


import com.project.repo.model.UserRole;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PermissionRoleSpecifications {

  private PermissionRoleSpecifications() {
  }

  public static Specification<UserRole> withSearchParam(final Map<String, String> searchParams) {
    return new Specification<UserRole>() {
      private static final long serialVersionUID = -138296259061924096L;

      @Override
      public Predicate toPredicate(Root<UserRole> role, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        Pattern pattern = Pattern.compile(CriteriaSpecConstants.FILTER_REGEX);
        CharSequence searchParam = "";
        CharSequence paramKeyVal[] = null;
        for (Entry<String, String> param : searchParams.entrySet()) {
          if (param.toString().contains(CriteriaSpecConstants.SEARCHPARAMS)) {
            searchParam = param.toString().replaceAll(CriteriaSpecConstants.SEARCHPARAMS, "");
            if (searchParam.toString().contains("&&")) {
              paramKeyVal = searchParam.toString().split("&&");
              for (CharSequence paramVal : paramKeyVal) {
                predicates.addAll(formPredicate(paramVal, pattern, role, builder));
              }
            } else {
              predicates = formPredicate(searchParam, pattern, role, builder);
            }
          }
        }

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        return builder.and(predicates.toArray(predicatesArray));
      }
    };
  }

  private static String getContainsLikePattern(String searchTerm) {
    if (searchTerm == null || searchTerm.isEmpty()) {
      return "%";
    } else {
      return "%" + searchTerm.toLowerCase() + "%";
    }
  }

  private static List<Predicate> formPredicate(CharSequence paramVal, Pattern pattern, Root<UserRole> role,
                                               CriteriaBuilder builder) {
    List<Predicate> predicates = new ArrayList<>();
    CharSequence paramKeyVal[] = paramVal.toString().split("=");
    Matcher matcher = null;

    matcher = pattern.matcher(paramKeyVal[0]);
    if (matcher.find()) {
      predicates.add(builder.and(builder.like(builder.lower(role.get(matcher.group(1))),
              getContainsLikePattern(paramKeyVal[1].toString()))));
    }

    return predicates;
  }
}
