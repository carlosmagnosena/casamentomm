package br.com.magno.casamentomm.apresentacao;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jinstagram.Instagram;
import org.springframework.http.HttpRequest;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String SUCESSO = "success";
	protected static final String ERRO = "error";
	protected static final String INPUT = "input";

	protected HttpSession getSessaoDoUsuario() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	protected HttpServletRequest getHttpRequest() {
		return ServletActionContext.getRequest();
	}

	protected void adicionarCodigo(String code) {
		getSessaoDoUsuario().setAttribute("code", code);
	}

	protected String obterCodigo() {
		return (String) getSessaoDoUsuario().getAttribute("code");
	}
}
