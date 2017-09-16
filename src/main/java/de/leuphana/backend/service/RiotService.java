package de.leuphana.backend.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.client.RestTemplate;

import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.constant.Platform;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public abstract class RiotService<T> {

	protected RestTemplate restTemplate;
	protected RiotApi api;
	protected final ApiConfig config = new ApiConfig().setKey("RGAPI-6bba33b3-e6cd-41c2-87fb-0cf7037f5105");
	protected final Platform platform = Platform.EUW;
	private static WrappedSession session;
	private static Locale locale;

	public static Locale getLocale() {

		try {
			session = VaadinSession.getCurrent().getSession();
		} catch (Exception e) {
			// e.printStackTrace();
		}

		if (session != null) {
			if (locale == null) {
				session.setAttribute("locale", Locale.DE_DE);
			}
			locale = (Locale) session.getAttribute("locale");
		}
		return locale;
	}

	public static void setLocale(Locale locale, String language) {
		session = VaadinSession.getCurrent().getSession();
		session.setAttribute("locale", locale);
		System.out.println("Changed language to " + language);
	}

}
