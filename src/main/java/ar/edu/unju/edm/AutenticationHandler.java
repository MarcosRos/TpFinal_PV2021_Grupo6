package ar.edu.unju.edm;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AutenticationHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		boolean comun = false;
		boolean userRegistrador = false;
		boolean root = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("root")) {
				root = true;
				break;
			} else {
				if (grantedAuthority.getAuthority().equals("comun")) {
					comun = true;
					break;
				} else {
					userRegistrador = true;
					break;
				}
			}
		}
		if (root) {
			redirectStrategy.sendRedirect(request, response, "/turista/mostrar");
		} else {
			if (comun) {
				redirectStrategy.sendRedirect(request, response, "/poi/mostrar");
			} else {
				if (userRegistrador) {
					redirectStrategy.sendRedirect(request, response, "/registrador");
				} else {
					throw new IllegalStateException();
				}
			}
		}
	}
}
