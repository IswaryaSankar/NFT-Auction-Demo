/*
 * package com.example.demo.controller;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.example.demo.request.LoginRequest; import
 * com.example.demo.service.AuthService; import
 * com.justpayme.auth.payload.request.SignupRequest; import
 * com.justpayme.data.exception.UserBankDetailsException; import
 * com.justpayme.data.exception.UserInfoException;
 * 
 * @RestController
 * 
 * @CrossOrigin
 * 
 * @RequestMapping("/api/auth") public class AuthController {
 * 
 * @Autowired AuthService authService;
 * 
 *//**
	 * This Api helps to signin for
	 */
/*
 * @CrossOrigin
 * 
 * @PostMapping(value = "/signin", produces = "application/json") public
 * ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest
 * loginRequest) { return authService.signinService(loginRequest); }
 * 
 *//**
	 * This Api helps to signup for
	 */
/*
 * @CrossOrigin
 * 
 * @PostMapping(value = "/signup", produces = "application/json") public
 * ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest
 * signUpRequest) throws UserInfoException, UserBankDetailsException { return
 * authService.signupService(signUpRequest); }
 * 
 *//** This Api helps to generate the refresh token for signin user *//*
																		 * @PostMapping(value =
																		 * "/refreshtoken/{refreshToken}", produces =
																		 * "application/json") public ResponseEntity<?>
																		 * refreshtoken(
																		 * 
																		 * @Valid @PathVariable(value = "refreshToken",
																		 * required = true) String refreshToken) {
																		 * 
																		 * return
																		 * authService.refreshToken(refreshToken); } }
																		 */