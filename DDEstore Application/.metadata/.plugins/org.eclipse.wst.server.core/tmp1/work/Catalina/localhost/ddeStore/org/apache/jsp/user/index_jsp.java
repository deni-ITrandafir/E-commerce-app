/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.28
 * Generated at: 2025-01-14 20:08:54 UTC
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
import java.util.Arrays;
import java.util.ArrayList;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = new java.util.LinkedHashSet<>(6);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.util.Arrays");
    _jspx_imports_classes.add("java.util.Map");
    _jspx_imports_classes.add("java.util.ArrayList");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

   Map<String, List<String>> filters = (Map<String, List<String>>) request.getAttribute("filters");
   List<Map<String, String>> products = (List<Map<String, String>>) request.getAttribute("products");

      out.write('\r');
      out.write('\n');
 
    List<Integer> favoriteProductIds = (List<Integer>) request.getAttribute("favoriteProductIds");

      out.write('\r');
      out.write('\n');

    List<Map<String, String>> cartProducts = (List<Map<String, String>>) request.getAttribute("cartProducts");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>DDEstore</title>\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/ddeStore/css/user.css\">\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\r\n");
      out.write("    \r\n");
      out.write("    <style>\r\n");
      out.write("	    .popup {\r\n");
      out.write("	    position: fixed;\r\n");
      out.write("	    bottom: 20px;\r\n");
      out.write("	    right: 20px;\r\n");
      out.write("	    background-color: #4caf50;\r\n");
      out.write("	    color: white;\r\n");
      out.write("	    padding: 10px 20px;\r\n");
      out.write("	    border-radius: 5px;\r\n");
      out.write("	    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);\r\n");
      out.write("	    z-index: 1000;\r\n");
      out.write("	    animation: fadeInOut 3s ease-in-out;\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		.popup.error {\r\n");
      out.write("		    background-color: #f44336; /* RoÈu pentru erori */\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		@keyframes fadeInOut {\r\n");
      out.write("		    0% { opacity: 0; transform: translateY(20px); }\r\n");
      out.write("		    10% { opacity: 1; transform: translateY(0); }\r\n");
      out.write("		    90% { opacity: 1; transform: translateY(0); }\r\n");
      out.write("		    100% { opacity: 0; transform: translateY(20px); }\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- Navbar -->\r\n");
      out.write("    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark fixed-top\">\r\n");
      out.write("        <div class=\"container-fluid\">\r\n");
      out.write("            <a class=\"navbar-brand\" href=\"/ddeStore/user/index.jsp\">\r\n");
      out.write("                <img src=\"/ddeStore/assets/logo2.jpg\" alt=\"Logo\" style=\"width: 130px;\">\r\n");
      out.write("            </a>\r\n");
      out.write("            <form class=\"d-flex w-50 position-relative\">\r\n");
      out.write("			    <input class=\"form-control me-2\" type=\"search\" id=\"search\" name=\"query\" placeholder=\"Cauta produse...\" aria-label=\"Search\" onkeyup=\"searchProducts(this.value)\">\r\n");
      out.write("			    <button class=\"btn buton-cauta position-absolute end-0 py-1 px-4\"  type=\"submit\" \r\n");
      out.write("			    		style=\"height: 100%; border-radius: 0px 20px 20px 0px; margin-right: 7px; background-color: #343737; color: white;\">\r\n");
      out.write("			        <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width:24px;\" fill=\"none\" viewBox=\"0 0 24 24\" strokeWidth={1.5} stroke=\"currentColor\">\r\n");
      out.write("			            <path strokeLinecap=\"round\" strokeLinejoin=\"round\" d=\"m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z\" />\r\n");
      out.write("			        </svg>\r\n");
      out.write("			    </button>\r\n");
      out.write("			</form>\r\n");
      out.write("\r\n");
      out.write("			<div id=\"autocomplete-list\" class=\"autocomplete-items\"></div>\r\n");
      out.write("            <ul class=\"navbar-nav mb-2 mb-lg-0\">\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"/ddeStore/CartServlet\">\r\n");
      out.write("                        <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width:30px; height:30px;\" fill=\"none\" viewBox=\"0 0 24 24\" strokeWidth=\"1.5\" stroke=\"currentColor\" class=\"size-6\">\r\n");
      out.write("                          <path strokeLinecap=\"round\" strokeLinejoin=\"round\" d=\"M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z\" />\r\n");
      out.write("                        </svg>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"/ddeStore/FavoritesServlet\">\r\n");
      out.write("					    <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width:30px; height:30px;\" fill=\"none\" viewBox=\"0 0 24 24\" stroke-width=\"1.5\" stroke=\"currentColor\" >\r\n");
      out.write("					        <path stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z\" />\r\n");
      out.write("					    </svg>\r\n");
      out.write("					</a>\r\n");
      out.write("\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item dropdown\">\r\n");
      out.write("                    <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\r\n");
      out.write("                        <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width:30px; height:30px;\" fill=\"none\" viewBox=\"0 0 24 24\" strokeWidth=\"1.5\" stroke=\"currentColor\" class=\"size-6\">\r\n");
      out.write("                          <path strokeLinecap=\"round\" strokeLinejoin=\"round\" d=\"M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z\" />\r\n");
      out.write("                        </svg>\r\n");
      out.write("                        ");
      out.print( session.getAttribute("name") );
      out.write("\r\n");
      out.write("                    </a>\r\n");
      out.write("                    <ul class=\"dropdown-menu dropdown-menu-end\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("                        <li><a class=\"dropdown-item\" href=\"/ddeStore/user/account.jsp\">Cont</a></li>\r\n");
      out.write("                        <li><hr class=\"dropdown-divider\"></li>\r\n");
      out.write("                        <li><a class=\"dropdown-item\" href=\"/ddeStore/logout\">Logout</a></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container-fluid d-flex justify-content-center align-items-center\" style=\"margin-top: 100px; \">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <!-- Sidebar Filters -->\r\n");
      out.write("            <div class=\"col-filtre\">\r\n");
      out.write("                <div class=\"bg-light p-4\">\r\n");
      out.write("                    <h5>Filtre</h5>\r\n");
      out.write("                    <form id=\"filterForm\" action=\"/ddeStore/ProductFilterServlet\" method=\"get\">\r\n");
      out.write("                        <!-- Categorie -->\r\n");
      out.write("                        <div class=\"mb-3\">\r\n");
      out.write("                            <h6>Categorie</h6>\r\n");
      out.write("                            ");
 if (filters != null && filters.containsKey("categories")) {
                                List<String> selectedCategories = request.getParameterValues("categories") != null ? Arrays.asList(request.getParameterValues("categories")) : new ArrayList<>();
                                for (String category : filters.get("categories")) { 
      out.write("\r\n");
      out.write("                                    <div class=\"form-check\">\r\n");
      out.write("                                        <input class=\"form-check-input\" type=\"checkbox\" name=\"categories\" value=\"");
      out.print( category );
      out.write("\" \r\n");
      out.write("                                            ");
 if (selectedCategories.contains(category)) { 
      out.write("checked");
 } 
      out.write(">\r\n");
      out.write("                                        <label class=\"form-check-label\">");
      out.print( category );
      out.write("</label>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                            ");
 }} 
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <!-- Brand -->\r\n");
      out.write("                        <div class=\"mb-3\">\r\n");
      out.write("                            <h6>Brand</h6>\r\n");
      out.write("                            ");
 if (filters != null && filters.containsKey("brands")) {
                                List<String> selectedBrands = request.getParameterValues("brands") != null ? Arrays.asList(request.getParameterValues("brands")) : new ArrayList<>();
                                for (String brand : filters.get("brands")) { 
      out.write("\r\n");
      out.write("                                    <div class=\"form-check\">\r\n");
      out.write("                                        <input class=\"form-check-input\" type=\"checkbox\" name=\"brands\" value=\"");
      out.print( brand );
      out.write("\" \r\n");
      out.write("                                            ");
 if (selectedBrands.contains(brand)) { 
      out.write("checked");
 } 
      out.write(">\r\n");
      out.write("                                        <label class=\"form-check-label\">");
      out.print( brand );
      out.write("</label>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                            ");
 }} 
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write(" 						\r\n");
      out.write("                        <!-- Memorie -->\r\n");
      out.write("                        <div class=\"mb-3\">\r\n");
      out.write("                            <h6>Memorie</h6>\r\n");
      out.write("                            ");
 if (filters != null && filters.containsKey("memories")) {
                                List<String> selectedMemories = request.getParameterValues("memories") != null ? Arrays.asList(request.getParameterValues("memories")) : new ArrayList<>();
                                for (String memory : filters.get("memories")) { 
      out.write("\r\n");
      out.write("                                    <div class=\"form-check\">\r\n");
      out.write("                                        <input class=\"form-check-input\" type=\"checkbox\" name=\"memories\" value=\"");
      out.print( memory );
      out.write("\" \r\n");
      out.write("                                            ");
 if (selectedMemories.contains(memory)) { 
      out.write("checked");
 } 
      out.write(">\r\n");
      out.write("                                        <label class=\"form-check-label\">");
      out.print( memory );
      out.write("</label>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                            ");
 }} 
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <!-- Culoare -->\r\n");
      out.write("                        <div class=\"mb-3\">\r\n");
      out.write("                            <h6>Culoare</h6>\r\n");
      out.write("                            ");
 if (filters != null && filters.containsKey("colors")) {
                                List<String> selectedColors = request.getParameterValues("colors") != null ? Arrays.asList(request.getParameterValues("colors")) : new ArrayList<>();
                                for (String color : filters.get("colors")) { 
      out.write("\r\n");
      out.write("                                    <div class=\"form-check\">\r\n");
      out.write("                                        <input class=\"form-check-input\" type=\"checkbox\" name=\"colors\" value=\"");
      out.print( color );
      out.write("\" \r\n");
      out.write("                                            ");
 if (selectedColors.contains(color)) { 
      out.write("checked");
 } 
      out.write(">\r\n");
      out.write("                                        <label class=\"form-check-label\">");
      out.print( color );
      out.write("</label>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                            ");
 }} 
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <!-- Price -->\r\n");
      out.write("                        <div class=\"mb-3\">\r\n");
      out.write("                            <label for=\"priceMin\" class=\"form-label\">Pret Minim</label>\r\n");
      out.write("                            <input type=\"number\" id=\"priceMin\" name=\"priceMin\" class=\"form-control\" value=\"");
      out.print( request.getParameter("priceMin") != null ? request.getParameter("priceMin") : "" );
      out.write("\">\r\n");
      out.write("                            <label for=\"priceMax\" class=\"form-label\">Pret Maxim</label>\r\n");
      out.write("                            <input type=\"number\" id=\"priceMax\" name=\"priceMax\" class=\"form-control\" value=\"");
      out.print( request.getParameter("priceMax") != null ? request.getParameter("priceMax") : "" );
      out.write("\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-success w-100\">Aplica filtre</button>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            \r\n");
      out.write("\r\n");
      out.write("            <!-- Products Section -->\r\n");
      out.write("			<div class=\"col-md-9\" style=\"margin-right:20px;\">\r\n");
      out.write("			    <div class=\"row\">\r\n");
      out.write("				    <!-- Best seller din ultimele 6 luni -->\r\n");
      out.write("					<h4 class=\"text-left p-2\"><b>Best seller la Telefoane din ultimele 6 luni</b></h4>\r\n");
      out.write("					<div class=\"row\">\r\n");
      out.write("						    ");
 
						        List<Map<String, String>> bestSellingProducts = (List<Map<String, String>>) request.getAttribute("bestSellingProducts");
						        if (bestSellingProducts != null && !bestSellingProducts.isEmpty()) {
						            for (Map<String, String> product : bestSellingProducts) { 
						     
      out.write("\r\n");
      out.write("					        <div class=\"col-md-4\">\r\n");
      out.write("					            <div class=\"card mb-4\">\r\n");
      out.write("					                <!-- Icon pentru favorite -->\r\n");
      out.write("									<svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width:25px; height:25px; cursor: pointer;\"\r\n");
      out.write("									     fill=\"");
      out.print( favoriteProductIds.contains(Integer.parseInt(product.get("ID_Produs"))) ? "red" : "none" );
      out.write("\"\r\n");
      out.write("									     viewBox=\"0 0 24 24\" stroke-width=\"1.5\" stroke=\"currentColor\" class=\"favorite-icon\"\r\n");
      out.write("									     onclick=\"toggleFavorite('");
      out.print( product.get("ID_Produs") );
      out.write("', this)\">\r\n");
      out.write("									    <path stroke-linecap=\"round\" stroke-linejoin=\"round\"\r\n");
      out.write("									          d=\"M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z\" />\r\n");
      out.write("									</svg>\r\n");
      out.write("					\r\n");
      out.write("					                <!-- Imaginea produsului -->\r\n");
      out.write("					                <img src=\"");
      out.print( product.get("Imagine") );
      out.write("\" class=\"card-img-top\" alt=\"");
      out.print( product.get("Brand") );
      out.write("\">\r\n");
      out.write("					\r\n");
      out.write("					                <div class=\"card-body\">\r\n");
      out.write("					                    <!-- Titlul produsului -->\r\n");
      out.write("					                    <h5 class=\"card-title\">");
      out.print( product.get("Brand") );
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print( product.get("Model") );
      out.write("</h5>\r\n");
      out.write("					\r\n");
      out.write("					                    <!-- InformaÈii suplimentare -->\r\n");
      out.write("					                    <p class=\"card-text\">");
      out.print( product.get("Memorie") );
      out.write(',');
      out.write(' ');
      out.print( product.get("Culoare") );
      out.write("</p>\r\n");
      out.write("					\r\n");
      out.write("					                    <!-- PreÈul produsului -->\r\n");
      out.write("					                    <p class=\"card-price\">\r\n");
      out.write("					                        ");
      out.print( product.get("Pret") );
      out.write(" Lei\r\n");
      out.write("					                    </p>\r\n");
      out.write("					\r\n");
      out.write("					                    <!-- Buton pentru adÄugare Ã®n coÈ -->\r\n");
      out.write("										<button class=\"cart-btn\" \r\n");
      out.write("												onclick=\"addToCart('");
      out.print( product.get("ID_Produs") );
      out.write("')\">\r\n");
      out.write("										    <svg xmlns=\"http://www.w3.org/2000/svg\" fill=\"none\" viewBox=\"0 0 24 24\" strokeWidth=\"1.5\" stroke=\"currentColor\" class=\"size-6\">\r\n");
      out.write("										        <path strokeLinecap=\"round\" strokeLinejoin=\"round\" d=\"M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z\" />\r\n");
      out.write("										    </svg>\r\n");
      out.write("										</button>\r\n");
      out.write("					                </div>\r\n");
      out.write("					            </div>\r\n");
      out.write("					        </div>\r\n");
      out.write("					    ");
 
					            }
					        } else { 
					    
      out.write("\r\n");
      out.write("					        <p class=\"text-center\">Nu exista produse vandute in categoria Telefoane in ultimele 6 luni.</p>\r\n");
      out.write("					    ");
 } 
      out.write("\r\n");
      out.write("					</div>\r\n");
      out.write("\r\n");
      out.write("			\r\n");
      out.write("			    	<!-- Toate produsele -->\r\n");
      out.write("			    	<h4 class=\"text-left p-2\"><b>Toate produsele noastre</b></h4>\r\n");
      out.write("			        ");
 if (products != null && !products.isEmpty()) {
			            for (Map<String, String> product : products) { 
      out.write("\r\n");
      out.write("			                <div class=\"col-md-4\">\r\n");
      out.write("			                    <div class=\"card mb-4\">\r\n");
      out.write("			                        <!-- Icon pentru favorite -->\r\n");
      out.write("									<svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width:25px; height:25px; cursor: pointer;\"\r\n");
      out.write("									     fill=\"");
      out.print( favoriteProductIds.contains(Integer.parseInt(product.get("ID_Produs"))) ? "red" : "none" );
      out.write("\"\r\n");
      out.write("									     viewBox=\"0 0 24 24\" stroke-width=\"1.5\" stroke=\"currentColor\" class=\"favorite-icon\"\r\n");
      out.write("									     onclick=\"toggleFavorite('");
      out.print( product.get("ID_Produs") );
      out.write("', this)\">\r\n");
      out.write("									    <path stroke-linecap=\"round\" stroke-linejoin=\"round\"\r\n");
      out.write("									          d=\"M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z\" />\r\n");
      out.write("									</svg>\r\n");
      out.write("			                        <!-- Imaginea produsului -->\r\n");
      out.write("			                        <img src=\"");
      out.print( product.get("Imagine") );
      out.write("\" class=\"card-img-top\" alt=\"");
      out.print( product.get("Brand") );
      out.write("\">\r\n");
      out.write("			\r\n");
      out.write("			                        <div class=\"card-body\">\r\n");
      out.write("			                            <!-- Titlul produsului -->\r\n");
      out.write("			                            <h5 class=\"card-title\">");
      out.print( product.get("Brand") );
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print( product.get("Model") );
      out.write("</h5>\r\n");
      out.write("			\r\n");
      out.write("			                            <!-- InformaÈii suplimentare -->\r\n");
      out.write("			                            <p class=\"card-text\">");
      out.print( product.get("Memorie") );
      out.write(',');
      out.write(' ');
      out.print( product.get("Culoare") );
      out.write("</p>\r\n");
      out.write("			\r\n");
      out.write("			                            <!-- PreÈul produsului -->\r\n");
      out.write("			                            <p class=\"card-price\">\r\n");
      out.write("			                                ");
      out.print( product.get("Pret") );
      out.write("<sup></sup> Lei\r\n");
      out.write("			                            </p>\r\n");
      out.write("			\r\n");
      out.write("			                            <!-- Buton pentru adÄugare Ã®n coÈ -->\r\n");
      out.write("										<button class=\"cart-btn\" \r\n");
      out.write("												onclick=\"addToCart('");
      out.print( product.get("ID_Produs") );
      out.write("')\">\r\n");
      out.write("										    <svg xmlns=\"http://www.w3.org/2000/svg\" fill=\"none\" viewBox=\"0 0 24 24\" strokeWidth=\"1.5\" stroke=\"currentColor\" class=\"size-6\">\r\n");
      out.write("										        <path strokeLinecap=\"round\" strokeLinejoin=\"round\" d=\"M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z\" />\r\n");
      out.write("										    </svg>\r\n");
      out.write("										</button>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("			                        </div>\r\n");
      out.write("			                    </div>\r\n");
      out.write("			                </div>\r\n");
      out.write("			        ");
 }} else { 
      out.write("\r\n");
      out.write("			            <p class=\"text-center\">Nu exista produse pentru filtrele selectate.</p>\r\n");
      out.write("			        ");
 } 
      out.write("\r\n");
      out.write("			        \r\n");
      out.write("			        \r\n");
      out.write("			        \r\n");
      out.write("			        \r\n");
      out.write("<!-- RecomandÄri produse -->\r\n");
      out.write("<h4 class=\"text-left p-2\"><b>Cumpara din nou</b></h4>\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("    ");
 
        List<Map<String, String>> recommendedProducts = (List<Map<String, String>>) request.getAttribute("recommendedProducts");
        if (recommendedProducts != null && !recommendedProducts.isEmpty()) {
            for (Map<String, String> product : recommendedProducts) { 
     
      out.write("\r\n");
      out.write("    <div class=\"col-md-4\">\r\n");
      out.write("        <div class=\"card mb-4\">\r\n");
      out.write("            <!-- Icon pentru favorite -->\r\n");
      out.write("            <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width:25px; height:25px; cursor: pointer;\"\r\n");
      out.write("                 fill=\"");
      out.print( favoriteProductIds.contains(Integer.parseInt(product.get("ID_Produs"))) ? "red" : "none" );
      out.write("\"\r\n");
      out.write("                 viewBox=\"0 0 24 24\" stroke-width=\"1.5\" stroke=\"currentColor\" class=\"favorite-icon\"\r\n");
      out.write("                 onclick=\"toggleFavorite('");
      out.print( product.get("ID_Produs") );
      out.write("', this)\">\r\n");
      out.write("                <path stroke-linecap=\"round\" stroke-linejoin=\"round\"\r\n");
      out.write("                      d=\"M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z\" />\r\n");
      out.write("            </svg>\r\n");
      out.write("\r\n");
      out.write("            <!-- Imaginea produsului -->\r\n");
      out.write("            <img src=\"");
      out.print( product.get("Imagine") );
      out.write("\" class=\"card-img-top\" alt=\"");
      out.print( product.get("Brand") );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <!-- Titlul produsului -->\r\n");
      out.write("                <h5 class=\"card-title\">");
      out.print( product.get("Brand") );
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print( product.get("Model") );
      out.write("</h5>\r\n");
      out.write("\r\n");
      out.write("                <!-- InformaÈii suplimentare -->\r\n");
      out.write("                <p class=\"card-text\">");
      out.print( product.get("Memorie") );
      out.write(',');
      out.write(' ');
      out.print( product.get("Culoare") );
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("                <!-- PreÈul produsului -->\r\n");
      out.write("                <p class=\"card-price\">\r\n");
      out.write("                    ");
      out.print( product.get("Pret") );
      out.write(" Lei\r\n");
      out.write("                </p>\r\n");
      out.write("\r\n");
      out.write("                <!-- Buton pentru adÄugare Ã®n coÈ -->\r\n");
      out.write("                <button class=\"cart-btn\" \r\n");
      out.write("                        onclick=\"addToCart('");
      out.print( product.get("ID_Produs") );
      out.write("')\">\r\n");
      out.write("                    <svg xmlns=\"http://www.w3.org/2000/svg\" fill=\"none\" viewBox=\"0 0 24 24\" strokeWidth=\"1.5\" stroke=\"currentColor\" class=\"size-6\">\r\n");
      out.write("                        <path strokeLinecap=\"round\" strokeLinejoin=\"round\" d=\"M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z\" />\r\n");
      out.write("                    </svg>\r\n");
      out.write("                </button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
 
            }
        } else { 
    
      out.write("\r\n");
      out.write("    <p class=\"text-center\">Nu existÄ produse recomandate bazate pe achiziÈiile tale.</p>\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("			        \r\n");
      out.write("			        \r\n");
      out.write("			        \r\n");
      out.write("			        \r\n");
      out.write("			        \r\n");
      out.write("			    </div>\r\n");
      out.write("			</div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    <script src=\"/ddeStore/js/user.js\"></script>\r\n");
      out.write("    <script>\r\n");
      out.write("	    function toggleFavorite(productId, iconElement) {\r\n");
      out.write("	        // DeterminÄ acÈiunea pe baza atributului `fill`\r\n");
      out.write("	        const currentFill = window.getComputedStyle(iconElement).getPropertyValue(\"fill\");\r\n");
      out.write("	        const action = currentFill === \"rgb(255, 0, 0)\" ? \"remove\" : \"add\"; // VerificÄ dacÄ este roÈu\r\n");
      out.write("	\r\n");
      out.write("	        // CreeazÄ corpul cererii pentru fetch\r\n");
      out.write("	        const formData = new URLSearchParams();\r\n");
      out.write("	        formData.append(\"action\", action);\r\n");
      out.write("	        formData.append(\"id\", productId);\r\n");
      out.write("	\r\n");
      out.write("	        // Trimite cererea cÄtre servlet\r\n");
      out.write("	        fetch('/ddeStore/FavoritesServlet', {\r\n");
      out.write("	            method: 'POST',\r\n");
      out.write("	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },\r\n");
      out.write("	            body: formData.toString(),\r\n");
      out.write("	        })\r\n");
      out.write("	        .then(response => response.json())\r\n");
      out.write("	        .then(data => {\r\n");
      out.write("	            if (data.success) {\r\n");
      out.write("	                // ActualizeazÄ culoarea inimii pe baza acÈiunii\r\n");
      out.write("	                if (action === \"add\") {\r\n");
      out.write("	                    iconElement.style.fill = \"red\"; // SeteazÄ culoarea ca roÈu\r\n");
      out.write("	                } else if (action === \"remove\") {\r\n");
      out.write("	                    iconElement.style.fill = \"none\"; // SeteazÄ culoarea ca transparent\r\n");
      out.write("	                }\r\n");
      out.write("	                showPopup(data.message || (action === \"add\" ? \"Produs adÄugat la favorite!\" : \"Produs eliminat din favorite!\"));\r\n");
      out.write("	            } else {\r\n");
      out.write("	                showPopup(data.message || \"Eroare la procesare.\");\r\n");
      out.write("	            }\r\n");
      out.write("	        })\r\n");
      out.write("	        .catch(error => {\r\n");
      out.write("	            console.error(\"Eroare:\", error);\r\n");
      out.write("	            showPopup(\"A apÄrut o eroare la server. ÃncercaÈi din nou.\");\r\n");
      out.write("	        });\r\n");
      out.write("	    }\r\n");
      out.write("	\r\n");
      out.write("	    function addToCart(productId) {\r\n");
      out.write("	        const formData = new URLSearchParams();\r\n");
      out.write("	        formData.append(\"id\", productId);\r\n");
      out.write("	\r\n");
      out.write("	        fetch('/ddeStore/CartAddServlet', {\r\n");
      out.write("	            method: 'POST',\r\n");
      out.write("	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },\r\n");
      out.write("	            body: formData.toString(),\r\n");
      out.write("	        })\r\n");
      out.write("	        .then(response => response.json())\r\n");
      out.write("	        .then(data => {\r\n");
      out.write("	            if (data.success) {\r\n");
      out.write("	                showPopup(data.message || \"Produs adÄugat Ã®n coÈ!\");\r\n");
      out.write("	            } else {\r\n");
      out.write("	                showPopup(data.message || \"Eroare la adÄugarea Ã®n coÈ.\");\r\n");
      out.write("	            }\r\n");
      out.write("	        })\r\n");
      out.write("	        .catch(error => {\r\n");
      out.write("	            console.error(\"Eroare:\", error);\r\n");
      out.write("	            showPopup(\"A apÄrut o eroare la server. ÃncercaÈi din nou.\");\r\n");
      out.write("	        });\r\n");
      out.write("	    }\r\n");
      out.write("	\r\n");
      out.write("	    function showPopup(message) {\r\n");
      out.write("	        const popup = document.createElement('div');\r\n");
      out.write("	        popup.className = 'popup';\r\n");
      out.write("	        popup.textContent = message;\r\n");
      out.write("	\r\n");
      out.write("	        document.body.appendChild(popup);\r\n");
      out.write("	\r\n");
      out.write("	        setTimeout(() => {\r\n");
      out.write("	            popup.remove();\r\n");
      out.write("	        }, 3000); // Popup-ul dispare dupÄ 3 secunde\r\n");
      out.write("	    }\r\n");
      out.write("	\r\n");
      out.write("	    \r\n");
      out.write("	    \r\n");
      out.write("	    \r\n");
      out.write("	</script>\r\n");
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
