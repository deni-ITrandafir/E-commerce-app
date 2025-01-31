/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.28
 * Generated at: 2024-12-30 20:17:15 UTC
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

public final class addProduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

    // VerificÄ dacÄ utilizatorul este autentificat
    if (session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>AdaugÄ Produs</title>\r\n");
      out.write("\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"/ddeStore/css/index.css\" rel=\"stylesheet\">\r\n");
      out.write("    <style>\r\n");
      out.write("        .popup {\r\n");
      out.write("            position: fixed;\r\n");
      out.write("            bottom: 20px;\r\n");
      out.write("            right: 20px;\r\n");
      out.write("            background-color: #4caf50;\r\n");
      out.write("            color: white;\r\n");
      out.write("            padding: 10px 20px;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);\r\n");
      out.write("            z-index: 1000;\r\n");
      out.write("            animation: fadeInOut 3s ease-in-out;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        @keyframes fadeInOut {\r\n");
      out.write("            0% { opacity: 0; transform: translateY(20px); }\r\n");
      out.write("            10% { opacity: 1; transform: translateY(0); }\r\n");
      out.write("            90% { opacity: 1; transform: translateY(0); }\r\n");
      out.write("            100% { opacity: 0; transform: translateY(20px); }\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"d-flex\">\r\n");
      out.write("        <!-- Sidebar -->\r\n");
      out.write("        <div class=\"sidebar bg-dark text-white p-3\">\r\n");
      out.write("            <div class=\"sidebar-header text-center py-4\">\r\n");
      out.write("                <img src=\"/ddeStore/assets/logo2.jpg\" alt=\"Logo\" class=\"img-fluid rounded-circle mb-2\" style=\"width: 80px;\">\r\n");
      out.write("                <h5>Admin Panel</h5>\r\n");
      out.write("            </div>\r\n");
      out.write("            <ul class=\"nav flex-column\">\r\n");
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
      out.write("            <div class=\"container\">\r\n");
      out.write("                <h2>Adauga un produs nou</h2>\r\n");
      out.write("                <form action=\"/ddeStore/AddProductServlet\" method=\"post\" enctype=\"multipart/form-data\" class=\"mt-4\">\r\n");
      out.write("                \r\n");
      out.write("                    <!-- Categorie -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"idCategorie\" class=\"form-label\">Categorie</label>\r\n");
      out.write("                        <select class=\"form-select\" id=\"idCategorie\" name=\"idCategorie\" required>\r\n");
      out.write("                            ");
 
                                List<Map<String, String>> categories = (List<Map<String, String>>) request.getAttribute("categories");
                                if (categories != null && !categories.isEmpty()) {
                                    for (Map<String, String> category : categories) {
                            
      out.write("\r\n");
      out.write("                            <option value=\"");
      out.print( category.get("ID_Categorie") );
      out.write('"');
      out.write('>');
      out.print( category.get("NumeCategorie") );
      out.write("</option>\r\n");
      out.write("                            ");
 
                                    }
                                } else { 
                            
      out.write("\r\n");
      out.write("                            <option value=\"\">Nu exista categorii</option>\r\n");
      out.write("                            ");
 } 
      out.write("\r\n");
      out.write("                        </select>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Brand -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"brand\" class=\"form-label\">Brand</label>\r\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"brand\" name=\"brand\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Model -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"model\" class=\"form-label\">Model</label>\r\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"model\" name=\"model\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Memorie -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"memorie\" class=\"form-label\">Memorie</label>\r\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"memorie\" name=\"memorie\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Culoare -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"culoare\" class=\"form-label\">Culoare</label>\r\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"culoare\" name=\"culoare\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Pret -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"pret\" class=\"form-label\">Pret</label>\r\n");
      out.write("                        <input type=\"number\" step=\"0.01\" class=\"form-control\" id=\"pret\" name=\"pret\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Stoc -->\r\n");
      out.write("                    <div class=\"mb-3\">\r\n");
      out.write("                        <label for=\"stoc\" class=\"form-label\">Stoc</label>\r\n");
      out.write("                        <input type=\"number\" class=\"form-control\" id=\"stoc\" name=\"stoc\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <!-- Imagini -->\r\n");
      out.write("					<div class=\"mb-3\">\r\n");
      out.write("					    <label for=\"imagini\" class=\"form-label\">Imagini</label>\r\n");
      out.write("					    <input type=\"file\" class=\"form-control\" id=\"imagini\" name=\"imagini\" multiple accept=\"image/*\">\r\n");
      out.write("					    <small class=\"form-text text-muted\">Poti selecta mai multe imagini.</small>\r\n");
      out.write("					</div>\r\n");
      out.write("\r\n");
      out.write("                    <!-- Save Button -->\r\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-primary\">Salveaza</button>\r\n");
      out.write("                    <a href=\"/ddeStore/ProductServlet\" class=\"btn btn-secondary\">Anuleaza</a>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    <!-- Popup -->\r\n");
      out.write("    ");
 String success = (String) request.getAttribute("success"); 
      out.write("\r\n");
      out.write("    ");
 if (success != null) { 
      out.write("\r\n");
      out.write("        <div class=\"popup\">");
      out.print( success );
      out.write("</div>\r\n");
      out.write("        <script>\r\n");
      out.write("            setTimeout(() => {\r\n");
      out.write("                window.location.href = \"/ddeStore/ProductServlet\";\r\n");
      out.write("            }, 3000); // RedirecteazÄ dupÄ 3 secunde\r\n");
      out.write("        </script>\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
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
