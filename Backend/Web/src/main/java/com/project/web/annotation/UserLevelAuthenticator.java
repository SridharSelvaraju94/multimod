
package com.project.web.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UserLevelAuthenticator {

	public String[] allow();
	
	public String[] deny() default {};
}
