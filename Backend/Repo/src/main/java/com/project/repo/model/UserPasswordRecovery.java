
package com.project.repo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_password_recovery")
public class UserPasswordRecovery {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "user_password_recovery_id")
	String userPasswordPecoveryId;

	@Column(name = "user_name")
	String username;

	@Column(name = "reset_token")
	String resetToken;

	@Column(name = "otp")
	String otp;

	@Column(name = "otp_created_on")
	LocalDateTime otpCreatedOn;

	@Column(name = "is_otp_valid")
	boolean isOtpValid;

	@Column(name = "is_token_valid")
	boolean isTokenValid;

	@Column(name = "max_attempts")
	int maxAttempts;

	/**
	 * @return the userPasswordPecoveryId
	 */
	public String getUserPasswordPecoveryId() {
		return userPasswordPecoveryId;
	}

	/**
	 * @param userPasswordPecoveryId
	 *            the userPasswordPecoveryId to set
	 */
	public void setUserPasswordPecoveryId(String userPasswordPecoveryId) {
		this.userPasswordPecoveryId = userPasswordPecoveryId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the resetToken
	 */
	public String getResetToken() {
		return resetToken;
	}

	/**
	 * @param resetToken
	 *            the resetToken to set
	 */
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp
	 *            the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the otpCreatedOn
	 */
	public LocalDateTime getOtpCreatedOn() {
		return otpCreatedOn;
	}

	/**
	 * @param otpCreatedOn
	 *            the otpCreatedOn to set
	 */
	public void setOtpCreatedOn(LocalDateTime otpCreatedOn) {
		this.otpCreatedOn = otpCreatedOn;
	}

	/**
	 * @return the isOtpValid
	 */
	public boolean isOtpValid() {
		return isOtpValid;
	}

	/**
	 * @param isOtpValid
	 *            the isOtpValid to set
	 */
	public void setOtpValid(boolean isOtpValid) {
		this.isOtpValid = isOtpValid;
	}

	/**
	 * @return the isTokenValid
	 */
	public boolean isTokenValid() {
		return isTokenValid;
	}

	/**
	 * @param isTokenValid
	 *            the isTokenValid to set
	 */
	public void setTokenValid(boolean isTokenValid) {
		this.isTokenValid = isTokenValid;
	}

	/**
	 * @return the maxAttempts
	 */
	public int getMaxAttempts() {
		return maxAttempts;
	}

	/**
	 * @param maxAttempts
	 *            the maxAttempts to set
	 */
	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

}
