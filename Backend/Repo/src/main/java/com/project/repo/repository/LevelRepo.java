package com.project.repo.repository;

import com.project.repo.model.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepo extends JpaRepository<UserLevel, String>, JpaSpecificationExecutor<UserLevel>{

	UserLevel getByLevelId(String levelId);
}
