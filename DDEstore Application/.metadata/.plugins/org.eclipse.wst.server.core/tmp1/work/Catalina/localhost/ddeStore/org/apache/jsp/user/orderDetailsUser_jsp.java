/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.28
 * Generated at: 2024-12-30 12:54:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.List;
import java.util.Map;

public final class orderDetailsUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(3);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.util.Map");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

    Map<String, String> orderDetails = (Map<String, String>) request.getAttribute("orderDetails");
    List<Map<String, String>> productDetails = (List<Map<String, String>>) request.getAttribute("productDetails");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Detalii Comanda</title>\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"/ddeStore/css/index.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <style>\r\n");
      out.write("    	.list-group-item:hover {\r\n");
      out.write("		    background-color: #495057; \r\n");
      out.write("		    color: #f8f9fa;\r\n");
      out.write("		}\r\n");
      out.write("		.btn-warning:hover {\r\n");
      out.write("		    background-color: #e0a800;\r\n");
      out.write("		}\r\n");
      out.write("		.btn-danger:hover {\r\n");
      out.write("		    background-color: #bd2130;\r\n");
      out.write("		}\r\n");
      out.write("		.border-top-white {\r\n");
      out.write("		    border-top: 1px solid white !important;\r\n");
      out.write("		    border-bottom: 0; \r\n");
      out.write("		    border-right: 0;\r\n");
      out.write("		    border-left: 0;\r\n");
      out.write("		    padding: 15px;\r\n");
      out.write("		}\r\n");
      out.write("		.border-bottom-white {\r\n");
      out.write("		    border-bottom: 1px solid white ; \r\n");
      out.write("		    border-right: 0;\r\n");
      out.write("		    border-left: 0;\r\n");
      out.write("		}\r\n");
      out.write("        .invoice-container {\r\n");
      out.write("            border: 1px solid #ddd;\r\n");
      out.write("            padding: 20px;\r\n");
      out.write("            border-radius: 10px;\r\n");
      out.write("            background: #f9f9f9;\r\n");
      out.write("        }\r\n");
      out.write("        .invoice-header {\r\n");
      out.write("            border-bottom: 1px solid #ddd;\r\n");
      out.write("            padding-bottom: 10px;\r\n");
      out.write("            margin-bottom: 20px;\r\n");
      out.write("        }\r\n");
      out.write("        .table-products {\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("        .table-products th, .table-products td {\r\n");
      out.write("            text-align: center;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"d-flex\">\r\n");
      out.write("        <!-- Sidebar -->\r\n");
      out.write("	        <div class=\"col-md-2 bg-dark text-light p-3\" style=\"height: 100vh;\">\r\n");
      out.write("	        	<div class=\"container-fluid\">\r\n");
      out.write("		            <a class=\"navbar-brand\" href=\"/ddeStore/ProductFilterServlet\">\r\n");
      out.write("		                <img src=\"/ddeStore/assets/logo2.jpg\" alt=\"Logo\" style=\"width: 150px; heigh:auto; margin-bottom:25px;\">\r\n");
      out.write("		            </a>\r\n");
      out.write("		        </div>\r\n");
      out.write("	            \r\n");
      out.write("	            <ul class=\"list-group bg-dark\">\r\n");
      out.write("	            	\r\n");
      out.write("	                <li class=\"list-group-item bg-dark text-light border-top-white\">\r\n");
      out.write("	                    <a href=\"/ddeStore/CartServlet\" class=\"text-decoration-none text-light\">Cos de cumparaturi</a>\r\n");
      out.write("	                </li>\r\n");
      out.write("	                <li class=\"list-group-item bg-dark text-light border-top-white\">\r\n");
      out.write("	                    <a href=\"/ddeStore/FavoritesServlet\" class=\"text-decoration-none text-light\">Lista favorite</a>\r\n");
      out.write("	                </li>\r\n");
      out.write("	                <li class=\"list-group-item bg-dark text-light border-top-white\">\r\n");
      out.write("	                    <a href=\"/ddeStore/AccountDetailsServlet\" class=\"text-decoration-none text-light\">Detalii cont</a>\r\n");
      out.write("	                </li>\r\n");
      out.write("	                <li class=\"list-group-item bg-dark text-light border-top-white border-bottom-white\">\r\n");
      out.write("	                    <a href=\"/ddeStore/OrderHistoryServlet\" class=\"text-decoration-none text-light\">Istoric comenzi</a>\r\n");
      out.write("	                </li>\r\n");
      out.write("	            </ul>\r\n");
      out.write("	            <div class=\" text-center\" style=\"margin-top: 320px;\">\r\n");
      out.write("	                <a href=\"/ddeStore/ProductFilterServlet\" class=\"btn btn-warning w-100\">Inapoi la produse</a>\r\n");
      out.write("	            </div>\r\n");
      out.write("	            <div class=\" text-center\" style=\"margin-top: 10px;\">\r\n");
      out.write("	                <a href=\"/ddeStore/logout\" class=\"btn btn-danger w-100\">Logout</a>\r\n");
      out.write("	            </div>\r\n");
      out.write("	        </div>\r\n");
      out.write("\r\n");
      out.write("	        <!-- Invoice Container -->\r\n");
      out.write("		    <div class=\"col-md-10 p-4\">\r\n");
      out.write("		        <div class=\"container\">\r\n");
      out.write("		            <div class=\"invoice-container\">\r\n");
      out.write("		                <div class=\"invoice-header text-left\">\r\n");
      out.write("		                    <h2>Factura Comanda</h2>\r\n");
      out.write("		                    ");
 if (orderDetails != null && !orderDetails.isEmpty()) { 
      out.write("\r\n");
      out.write("		                        <p><strong>ID Comanda:</strong> ");
      out.print( orderDetails.get("ID_Comanda") );
      out.write("</p>\r\n");
      out.write("		                        <p><strong>Data Comanda:</strong> ");
      out.print( orderDetails.get("DataComanda") );
      out.write("</p>\r\n");
      out.write("		                        <p><strong>Status:</strong> ");
      out.print( orderDetails.get("Status") );
      out.write("</p>\r\n");
      out.write("		                        <p><strong>Total:</strong> ");
      out.print( orderDetails.get("Total") );
      out.write(" RON</p>\r\n");
      out.write("		                    ");
 } 
      out.write("\r\n");
      out.write("		                </div>\r\n");
      out.write("		\r\n");
      out.write("		                <h4>Informatii Client</h4>\r\n");
      out.write("		                ");
 if (orderDetails != null && !orderDetails.isEmpty()) { 
      out.write("\r\n");
      out.write("		                    <p><strong>Nume:</strong> ");
      out.print( orderDetails.get("NumePrenume") );
      out.write("</p>\r\n");
      out.write("		                    <p><strong>Telefon:</strong> ");
      out.print( orderDetails.get("Telefon") );
      out.write("</p>\r\n");
      out.write("		                    <p><strong>Email:</strong> ");
      out.print( orderDetails.get("Email") );
      out.write("</p>\r\n");
      out.write("		                    <p><strong>Adresa:</strong> ");
      out.print( orderDetails.get("Adresa") );
      out.write("</p>\r\n");
      out.write("		                ");
 } else { 
      out.write("\r\n");
      out.write("		                    <p>Nu s-au gasit detalii despre client.</p>\r\n");
      out.write("		                ");
 } 
      out.write("\r\n");
      out.write("		\r\n");
      out.write("		                <h4>Produse Achizitionate</h4>\r\n");
      out.write("		                <table class=\"table table-bordered table-products\">\r\n");
      out.write("		                    <thead class=\"table-dark\">\r\n");
      out.write("		                        <tr>\r\n");
      out.write("		                            <th>Denumire Produs</th>\r\n");
      out.write("		                            <th>Cantitate</th>\r\n");
      out.write("		                            <th>Pret Unitar (RON)</th>\r\n");
      out.write("		                            <th>Total (RON)</th>\r\n");
      out.write("		                        </tr>\r\n");
      out.write("		                    </thead>\r\n");
      out.write("		                    <tbody>\r\n");
      out.write("		                        ");
 if (productDetails != null && !productDetails.isEmpty()) { 
      out.write("\r\n");
      out.write("		                            ");
 for (Map<String, String> product : productDetails) { 
      out.write("\r\n");
      out.write("		                                <tr>\r\n");
      out.write("		                                    <td>");
      out.print( product.get("DenumireProdus") );
      out.write("</td>\r\n");
      out.write("		                                    <td>");
      out.print( product.get("Cantitate") );
      out.write("</td>\r\n");
      out.write("		                                    <td>");
      out.print( product.get("PretUnitar") );
      out.write("</td>\r\n");
      out.write("		                                    <td>");
      out.print( product.get("TotalProdus") );
      out.write("</td>\r\n");
      out.write("		                                </tr>\r\n");
      out.write("		                            ");
 } 
      out.write("\r\n");
      out.write("		                        ");
 } else { 
      out.write("\r\n");
      out.write("		                            <tr>\r\n");
      out.write("		                                <td colspan=\"4\" class=\"text-center\">Nu exista produse achizitionate.</td>\r\n");
      out.write("		                            </tr>\r\n");
      out.write("		                        ");
 } 
      out.write("\r\n");
      out.write("		                    </tbody>\r\n");
      out.write("		                </table>\r\n");
      out.write("	                </div>\r\n");
      out.write("	            </div>\r\n");
      out.write("	        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
