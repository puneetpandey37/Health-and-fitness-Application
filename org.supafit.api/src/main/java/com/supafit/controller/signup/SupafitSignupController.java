package com.supafit.controller.signup;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.user.UserService;
import com.supafit.common.parser.CoachParser;
import com.supafit.common.parser.UserParser;
import com.supafit.common.security.SupafitUserLoginResponse;
import com.supafit.controller.coaches.CoachController;
import com.supafit.controller.signin.SupafitSigninController;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.user.model.User;
import com.supafit.model.coach.CoachSignupRequestDTO;
import com.supafit.model.coach.CoachSignupResponseDTO;
import com.supafit.model.user.UserDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/signup")
@Api(value = "signup", description = "Supafit local and Social SignUp")
@CrossOrigin
public class SupafitSignupController {

	private final String TAG = "SupafitSignupController";
	private UserService userService;
	private UserParser userParser;
	private CoachParser coachParser;

	@Autowired
	public void setCoachParser(CoachParser coachParser) {
		this.coachParser = coachParser;
	}

	@Autowired
	public void setUserParser(UserParser userParser) {
		this.userParser = userParser;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/social", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Signup with Authorization code of 3rd party Authorization Server", notes = "Signup with Authorization code of 3rd party Authorization Server", response = SupafitUserLoginResponse.class)
	public ResponseEntity<SupafitUserLoginResponse> socialSignup(@RequestParam("oauthServer") String oauthServer,
			@RequestParam(value = "origin", required = false) String origin,
			@RequestHeader("Authorization_Code") String authorizationCode,
			@RequestHeader("Client_Credentials") String clientCredentials) throws Exception {
		SupafitUserLoginResponse response = userService.socialLogin(oauthServer, authorizationCode, clientCredentials,
				origin);
		return new ResponseEntity<SupafitUserLoginResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/social/redirect", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Signup with Authorization code of 3rd party Authorization Server", notes = "Signup with Authorization code of 3rd party Authorization Server", response = SupafitUserLoginResponse.class)
	public ResponseEntity<SupafitUserLoginResponse> socialSignup(@RequestParam("oauthServer") String oauthServer,
			@RequestParam("code") String authorizationCode) throws Exception {
		SupafitUserLoginResponse response = userService.
				socialLogin(oauthServer, authorizationCode, null, "web");
		return new ResponseEntity<SupafitUserLoginResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/local/user", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Signup by filling in the user data", notes = "Signup by filling in the user data", response = UserDTO.class)
	public ResponseEntity<UserDTO> supafitUserSignup(@RequestBody UserDTO userDTO,
			@RequestHeader("Client_Credentials") String clientCredentials) throws Exception {
		User userEntity = userParser.convertToEntity(userDTO);
		User userResponse = userService.supafitSignup(userEntity, clientCredentials);
		UserDTO response = userParser.convertToDto(userResponse);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/local/coach", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Signup by filling in the user data", notes = "Signup by filling in the user data", response = CoachSignupResponseDTO.class)
	public ResponseEntity<CoachSignupResponseDTO> supafitCoachSignup(@Valid @RequestBody CoachSignupRequestDTO coachDTO,
			@RequestHeader("Client_Credentials") String clientCredentials) throws Exception {
		Coach coachEntity = coachParser.convertToEntity(coachDTO);
		Coach coachResponse = userService.supafitCoachSignup(coachEntity, clientCredentials);
		CoachSignupResponseDTO response = coachParser.convertCoachSignupResponseToDto(coachResponse);
		response.add(
				linkTo(methodOn(CoachController.class).getCoachDetail(coachResponse.getId())).withRel("self"));
		response.add(
				linkTo(methodOn(SupafitSigninController.class).coachSignIn("")).withRel("login"));
		return new ResponseEntity<CoachSignupResponseDTO>(response, HttpStatus.CREATED);
	}
}
