/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.28
 * Generated at: 2025-01-14 20:47:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.List;
import java.util.Map;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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


    if (session == null || session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Dashboard</title>\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"/ddeStore/css/index.css\" rel=\"stylesheet\">\r\n");
      out.write("    <style>\r\n");
      out.write("    	.card-height {\r\n");
      out.write("		    height: 200px;  \r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		.card-body-centered {\r\n");
      out.write("		    display: flex;\r\n");
      out.write("		    flex-direction: column;\r\n");
      out.write("		    align-items: center;\r\n");
      out.write("		    justify-content: center;\r\n");
      out.write("		    text-align: center;\r\n");
      out.write("		    height: 100%;  \r\n");
      out.write("		}\r\n");
      out.write("		.card-title{\r\n");
      out.write("			font-size: 24px;\r\n");
      out.write("		}\r\n");
      out.write("		.card-text {\r\n");
      out.write("		    display: flex;\r\n");
      out.write("		    align-items: center;\r\n");
      out.write("		    justify-content: center;\r\n");
      out.write("		    width: 100px;  \r\n");
      out.write("		    height: 100px; \r\n");
      out.write("		    background-color: #ffffff;  \r\n");
      out.write("		    color: #000;  \r\n");
      out.write("		    border-radius: 50%;  \r\n");
      out.write("		    font-size: 28px;  \r\n");
      out.write("		    font-weight: bold;\r\n");
      out.write("		    margin-bottom: 10px;  \r\n");
      out.write("		}\r\n");
      out.write("		.btn-logout{\r\n");
      out.write("			width: 200px;\r\n");
      out.write("			margin-top: 300px;\r\n");
      out.write("		}\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"d-flex\">\r\n");
      out.write("        <!-- Sidebar -->\r\n");
      out.write("        <div class=\"sidebar bg-dark text-white\">\r\n");
      out.write("            <div class=\"sidebar-header text-center py-4 \">\r\n");
      out.write("                <img src=\"/ddeStore/assets/logo2.jpg\" alt=\"Logo\" class=\"img-fluid rounded-circle  mb-2\" style=\"width: 80px;\">\r\n");
      out.write("                <h5>Admin Panel</h5>\r\n");
      out.write("            </div>\r\n");
      out.write("            <ul class=\"nav flex-column \">\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"/ddeStore/DashboardServlet\">Dashboard</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"/ddeStore/ProductServlet\">Produse</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"/ddeStore/CategoryServlet\">Categorii</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"/ddeStore/OrderServlet\">Comenzi</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link text-white\" href=\"/ddeStore/UserServlet\">Utilizatori</a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item mt-4\">\r\n");
      out.write("                    <form action=\"/ddeStore/logout\" method=\"get\" style=\"text-align: center;\">\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-danger btn-logout\">Logout</button>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- Content -->\r\n");
      out.write("        <div class=\"content flex-grow-1 p-4\">\r\n");
      out.write("            <h2>Dashboard</h2>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"col-md-4\">\r\n");
      out.write("                    <div class=\"card text-white bg-primary mb-3 card-height\">\r\n");
      out.write("                        <div class=\"card-body card-body-centered\">\r\n");
      out.write("                            <p class=\"card-text\">");
      out.print( request.getAttribute("totalUtilizatori") );
      out.write("</p>\r\n");
      out.write("                            <h5 class=\"card-title\">Total Utilizatori</h5>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-md-4\">\r\n");
      out.write("                    <div class=\"card text-white bg-success mb-3 card-height\">\r\n");
      out.write("					    <div class=\"card-body card-body-centered\">\r\n");
      out.write("					        <div class=\"card-text\">");
      out.print( request.getAttribute("totalProduse") );
      out.write("</div>\r\n");
      out.write("					        <h5 class=\"card-title\">Total Produse</h5>\r\n");
      out.write("					    </div>\r\n");
      out.write("					</div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-md-4\">\r\n");
      out.write("                    <div class=\"card text-white bg-info mb-3 card-height\">\r\n");
      out.write("                        <div class=\"card-body card-body-centered\">\r\n");
      out.write("                            <p class=\"card-text\">");
      out.print( request.getAttribute("totalCategorii") );
      out.write("</p>\r\n");
      out.write("                            <h5 class=\"card-title\">Total Categorii</h5>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"col-md-6\">\r\n");
      out.write("                    <div class=\"card text-white bg-warning mb-3 card-height\">\r\n");
      out.write("                        <div class=\"card-body card-body-centered\">\r\n");
      out.write("                            <p class=\"card-text\">");
      out.print( request.getAttribute("totalComenziAn") );
      out.write("</p>\r\n");
      out.write("                            <h5 class=\"card-title\">Comenzi Anul Curent</h5>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-md-6\">\r\n");
      out.write("                    <div class=\"card text-white bg-danger mb-3 card-height\">\r\n");
      out.write("                        <div class=\"card-body card-body-centered\">\r\n");
      out.write("                            <p class=\"card-text\">");
      out.print( request.getAttribute("totalComenziAzi") );
      out.write("</p>\r\n");
      out.write("                            <h5 class=\"card-title\">Comenzi Astazi</h5>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("			    <div class=\"col-md-4\">\r\n");
      out.write("			        <div class=\"card text-white\" style=\"background-color: #9966cc; \">\r\n");
      out.write("				        <div class=\"card-header\">\r\n");
      out.write("				            <h5>Top Categorii de Produse cele mai vandute (Luna Trecuta)</h5>\r\n");
      out.write("				        </div>\r\n");
      out.write("				        <div class=\"card-body\">\r\n");
      out.write("							<ul class=\"list-group\">\r\n");
      out.write("							    ");
 
							        List<Map<String, String>> topCategoriesLastMonth = 
							            (List<Map<String, String>>) request.getAttribute("topCategoriesLastMonth");
							        
							        if (topCategoriesLastMonth != null && !topCategoriesLastMonth.isEmpty()) { 
							            for (Map<String, String> category : topCategoriesLastMonth) {
							                String numeCategorie = category.get("NumeCategorie") != null ? category.get("NumeCategorie") : "FÄrÄ categorie";
							                String totalVandut = category.get("TotalVandut") != null ? category.get("TotalVandut") : "0";
							    
      out.write("\r\n");
      out.write("							        <li class=\"list-group-item d-flex justify-content-between align-items-center\">\r\n");
      out.write("							            ");
      out.print( numeCategorie );
      out.write("\r\n");
      out.write("							            <span class=\"badge bg-primary rounded-pill\">");
      out.print( totalVandut );
      out.write("</span>\r\n");
      out.write("							        </li>\r\n");
      out.write("							    ");
 
							            } 
							        } else { 
							    
      out.write("\r\n");
      out.write("							        <div class=\"alert alert-warning\">Nu existÄ date disponibile pentru luna trecutÄ.</div>\r\n");
      out.write("							    ");
 } 
      out.write("\r\n");
      out.write("							</ul>\r\n");
      out.write("\r\n");
      out.write("				        </div>\r\n");
      out.write("				    </div>\r\n");
      out.write("\r\n");
      out.write("			    </div >\r\n");
      out.write("			    \r\n");
      out.write("			    \r\n");
      out.write("				<div class=\"col-md-4\">\r\n");
      out.write("			    	<div class=\"card text-white\" style=\"background-color: #99cc00; height: 230px;\">\r\n");
      out.write("				        <div class=\"card-header text-center\">\r\n");
      out.write("				            <h6>Top 3 Utilizatori cu Cele Mai Multe Comenzi Verificate (luna trecuta)</h6>\r\n");
      out.write("				        </div>\r\n");
      out.write("			        <div class=\"card-body\">\r\n");
      out.write("			            ");

			                // ObÈine lista transmisÄ de servlet
			                List<Map<String, String>> topUsersDecember = (List<Map<String, String>>) request.getAttribute("topUsersDecember");
			                if (topUsersDecember != null && !topUsersDecember.isEmpty()) {
			            
      out.write("\r\n");
      out.write("			                <table class=\"table table-sm text-white\">\r\n");
      out.write("			                    <thead>\r\n");
      out.write("			                        <tr>\r\n");
      out.write("			                            <th>Nume Complet</th>\r\n");
      out.write("			                            <th>Total Comenzi</th>\r\n");
      out.write("			                        </tr>\r\n");
      out.write("			                    </thead>\r\n");
      out.write("			                    <tbody>\r\n");
      out.write("			                        ");

			                            // ItereazÄ prin lista Èi afiÈeazÄ datele
			                            for (Map<String, String> user : topUsersDecember) {
			                        
      out.write("\r\n");
      out.write("			                        <tr>\r\n");
      out.write("			                            <td>");
      out.print( user.get("NumeComplet") );
      out.write("</td>\r\n");
      out.write("			                            <td>");
      out.print( user.get("TotalComenzi") );
      out.write(" comenzi</td>\r\n");
      out.write("			                        </tr>\r\n");
      out.write("			                        ");

			                            }
			                        
      out.write("\r\n");
      out.write("			                    </tbody>\r\n");
      out.write("			                </table>\r\n");
      out.write("			            ");

			                } else {
			            
      out.write("\r\n");
      out.write("			                <p class=\"text-center\">Nu existÄ date despre utilizatori pentru luna trecuta.</p>\r\n");
      out.write("			            ");

			                }
			            
      out.write("\r\n");
      out.write("			        </div>\r\n");
      out.write("			    </div>\r\n");
      out.write("			</div>  \r\n");
      out.write("			<div class=\"col-md-4\">\r\n");
      out.write("			    <div class=\"card text-white card-height\"\r\n");
      out.write("			    		style=\"background-color: orange !important; height: 230px;\">\r\n");
      out.write("			        <div class=\"card-body\">\r\n");
      out.write("			            <h5 class=\"card-title\"><b>Cea Mai Profitabila Luna</b></h5>\r\n");
      out.write("			            ");
 Map<String, String> mostProfitableMonth = (Map<String, String>) request.getAttribute("mostProfitableMonth"); 
      out.write("\r\n");
      out.write("			            ");
 if (mostProfitableMonth != null && !mostProfitableMonth.isEmpty()) { 
      out.write("\r\n");
      out.write("			                <p>Luna: ");
      out.print( mostProfitableMonth.get("Luna") );
      out.write("</p>\r\n");
      out.write("			                <p>Vanzari Totale: ");
      out.print( mostProfitableMonth.get("TotalVanzari") );
      out.write(" RON</p>\r\n");
      out.write("			                <p>Din ");
      out.print( mostProfitableMonth.get("TotalComenzi") );
      out.write(" Comenzi</p>\r\n");
      out.write("			            ");
 } else { 
      out.write("\r\n");
      out.write("			                <p>Nu exista date disponibile.</p>\r\n");
      out.write("			            ");
 } 
      out.write("\r\n");
      out.write("			        </div>\r\n");
      out.write("			    </div>\r\n");
      out.write("			</div>\r\n");
      out.write("        </div>\r\n");
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
