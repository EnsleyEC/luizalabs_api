package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.controller.request.AccountRequest;
import br.com.luizalabsserverrest.controller.response.AuthResponse;
import br.com.luizalabsserverrest.controller.response.MessageResponse;
import br.com.luizalabsserverrest.security.JwtTokenUtil;
import br.com.luizalabsserverrest.service.JwtUserDetailsService;
import br.com.luizalabsserverrest.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "Auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @ApiOperation(value = "Faz login na API")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = AuthResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PostMapping(Constants.ROUTE_SIGN_IN)
    public ResponseEntity<?> signIn(@RequestBody AccountRequest accountRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountRequest.getUsername(),accountRequest.getPassword()));
        } catch (DisabledException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Usuário desabilitado."));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Usuário ou senha inválidos."));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(accountRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}

