import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class CartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        XmlSaveToHashmap productHashmap = new XmlSaveToHashmap("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");
        
        Item item = null;
        Accessory accessory = null;
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(null == cart) {
            cart = new ShoppingCart();
            session.setAttribute("cart" , cart);
        }
        ShoppingCartAcc cartAcc = (ShoppingCartAcc) session.getAttribute("cartAcc");
        if(null == cartAcc) {
            cartAcc = new ShoppingCartAcc();
            session.setAttribute("cartAcc" , cartAcc);
        }
        
		String username = (String) session.getAttribute("username");
        String usertype = (String) session.getAttribute("usertype");

        int itemNum = cart.getTotalNum() + cartAcc.getTotalNum();
        session.setAttribute("itemNum" , itemNum);

//======================================================================
        if(null != id){
            item = productHashmap.getItem(id);
            cart.addItem(item,1); 
            session.setAttribute("cart" , cart);

			itemNum = itemNum + 1;
            session.setAttribute("itemNum" , itemNum);
        }
        if(null != name){
            accessory = productHashmap.getAccessory(name);
            cartAcc.addAccessory(accessory,1);
            session.setAttribute("cartAcc" , cartAcc);

            itemNum = itemNum + 1;
            session.setAttribute("itemNum" , itemNum);
        }
        
        ArrayList itemsOrdered = (ArrayList)cart.getItemsOrdered();
        ArrayList accsOrdered  = (ArrayList)cartAcc.getAccsOrdered();

        PrintWriter out = response.getWriter();

        out.println("<html>");

        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
        out.println("<title> SmartPortables </title>");
        out.println("<link rel=\"stylesheet\" href=\"styles_product_list.css\" type=\"text/css\"/>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
        out.println("</head>");

        out.println("<body>");
        out.println("<div id=\"container\">");
        out.println("<header>");
        out.println("<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
        out.println("</header>");

     	//top navigation bar
        {
            out.println("<nav>");
            out.println("    <ul>");
            out.println("        <li><a href=\"index\">Home</a></li>");
            out.println("        <li><a href=\"PhoneServlet\">Phones</a></li>");
            out.println("        <li><a href=\"LaptopServlet\">Laptops</a></li>");
            out.println("        <li><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
            out.println("        <li><a href=\"SpeakersServlet\">Speakers</a></li>");
            out.println("        <li><a href=\"HeadphonesServlet\">Headphones</a></li>");
            out.println("        <li><a href=\"ExternalStorageServlet\">External Storage</a></li>");
            out.println("        <li class=\"end start selected\"\"><a href=\"CartServlet\">Cart(" + (cart.getTotalNum() + cartAcc.getTotalNum()) + ")</a></li>");
            out.println("    </ul>");
            out.println("</nav>");
        }

        out.println("<div id=\"body\">");

        //left sidebar
        {
            out.println("    <aside class=\"sidebar\">");
            out.println("        <ul>");
            out.println("           <li>");
            out.println("                <h4>Categories</h4>");
            out.println("                <ul>");
            out.println("                    <li><a href=\"index\">Home Page</a></li>");
            out.println("                    <li><a href=\"PhoneServlet\">Phones</a></li>");
            out.println("                    <li><a href=\"LaptopServlet\">Laptops</a></li>");
            out.println("                    <li><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
            out.println("                    <li><a href=\"SpeakersServlet\">Speakers</a></li>");
            out.println("                    <li><a href=\"HeadphonesServlet\">Headphones</a></li>");
            out.println("                    <li><a href=\"ExternalStorageServlet\">External storage</a></li>");
            out.println("                    <li><a href=\"AccessariesServlet\">Accessaries</a></li>");
            out.println("                    <li><a href=\"TrendingServlet\">Trending</a></li>");
            out.println("                </ul>");
            out.println("            </li>");

            out.println("            <li>");
            out.println("                <h4>Search</h4>");
            out.println("                <ul>");
            out.println("                    <li class=\"text\">");
            out.println("                        <form method=\"get\" class=\"searchform\" action=\"#\" >");
            out.println("                            <p>");
            out.println("                                <input type=\"text\" size=\"26\" value=\"\" name=\"s\" class=\"s\" />");
            out.println("                            </p>");
            out.println("                        </form>");    
            out.println("                    </li>");
            out.println("                </ul>");

            out.println("<li>");
            out.println("<h4>checkout</h4>");
            out.println("    <section class=\"cart-total\">");
            out.println("        <ul>");

            for(int i=0; i<itemsOrdered.size(); i++) {
                ItemOrder order = (ItemOrder)itemsOrdered.get(i);
                item = order.getItem();
                {
                    out.println("  <li><div style=\"float:left;text-align:left;width:70%\">" + item.name + "</div><div style=\"float:right;text-align:right;width:30%\"><b>$ " + item.price + "</b></div</li>");
                }
            }
            for(int i=0; i<accsOrdered.size(); i++) {
                AccessoryOrder order = (AccessoryOrder)accsOrdered.get(i);
                accessory = order.getAccessory();
                {
                    out.println("  <li><div style=\"float:left;text-align:left;width:70%\">" + accessory.name + "</div><div style=\"float:right;text-align:right;width:30%\"><b>$ " + accessory.price + "</b></div</li>");
                }
            }


            out.println("           <li>Total Value: $" + (cart.getTotalValue() + cartAcc.getTotalValue()) + "</li>");
            out.println("           <li>Item Number:" + (cart.getTotalNum() + cartAcc.getTotalNum()) + "</li>");
            if(null != username){
                out.println("       <li><center><a href=\"CheckOutServlet\" class=\"button\">Check Out</a></center></li>");
            }else{
                out.println("       <li><center><a href=\"LoginServlet\" class=\"button\">Login to Check Out</a></center></li>");
            }
            out.println("        </ul>");    
            out.println("    </section>");
            out.println("</li>");

            out.println("            </li>");
            out.println("        </ul>");    
            out.println("    </aside>");
        }

        //==========================================================

        out.println("   <section class=\"cart-list\">");
        if(null != id){
            item = productHashmap.getItem(id);
            out.println("        <div class=\"product\">");
            out.println("            <ul>");
            out.println("                <li><a href=\"PhoneOpenProductServlet?id=" + id + "\"><img class=\"content-image\" src=\"images/" + item.image + ".png\" alt=" + item.name + "/></a></li>");
            out.println("                <li>");
            out.println("                    <ul>");
            out.println("                        <li><b>item #" + item.id + "</b></li>");
            out.println("                        <li><b>$ " + item.price + "</b></li>");
            out.println("                        <li>name:  " + item.name);
            out.println("                        <li>color: " + item.color);
            out.println("                        <li>number:" + cart.getItemNum(item));
            out.println("                    </ul>");
            out.println("                </li>");
            out.println("            </ul>");
            out.println("        </div>");

            out.println("        <div class=\"clear\"></div>");
            /*{
                out.println("        <div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" data-interval=\"2500\">");
                //<!-- 轮播（Carousel）指标 -->
                out.println("            <ol class=\"carousel-indicators\">");
                out.println("                <li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>");
                out.println("                <li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>");
                out.println("                <li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>");
                out.println("            </ol>");
                //<!-- 轮播（Carousel）项目 -->
                out.println("            <div class=\"carousel-inner bor_btm\">");
                out.println("                <div class=\"item active\">");
                out.println("                    <ul>");
                out.println("                        <li style=\"width:32%;\">");
                {
                    Accessory accessory = productHashmap.getAccessory(item.accessories.get(0));
                    out.println("                        <div class=\"accessory\">");
                    out.println("                            <ul>");
                    out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
                    out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
                    out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
                    out.println("                                <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">View Review</a></li>");
                    out.println("                            </ul>");
                    out.println("                        </div>");
                }
                out.println("                        </li>");
                out.println("                        <li style=\"width:32%;\">");
                {
                    Accessory accessory = productHashmap.getAccessory(item.accessories.get(1));
                    out.println("                        <div class=\"accessory\">");
                    out.println("                            <ul>");
                    out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
                    out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
                    out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
                    out.println("                                <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">View Review</a></li>");
                    out.println("                            </ul>");
                    out.println("                        </div>");
                }
                out.println("                        </li>");
                out.println("                        <li style=\"width:32%;\">");
                {
                    Accessory accessory = productHashmap.getAccessory(item.accessories.get(2));
                    out.println("                        <div class=\"accessory\">");
                    out.println("                            <ul>");
                    out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
                    out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
                    out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
                    out.println("                                <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">View Review</a></li>");
                    out.println("                            </ul>");
                    out.println("                        </div>");
                }
                out.println("                        </li>");
                out.println("                    </ul>");
                out.println("                </div>");
                out.println("                <div class=\"item\">");
                out.println("                    <ul>");
                out.println("                        <li style=\"width:32%;\">");
                {
                    Accessory accessory = productHashmap.getAccessory(item.accessories.get(3));
                    out.println("                        <div class=\"accessory\">");
                    out.println("                            <ul>");
                    out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
                    out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
                    out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
                    out.println("                                <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">View Review</a></li>");
                    out.println("                            </ul>");
                    out.println("                        </div>");
                }
                out.println("                        </li>");
                out.println("                        <li style=\"width:32%;\">");
                {
                    Accessory accessory = productHashmap.getAccessory(item.accessories.get(4));
                    out.println("                        <div class=\"accessory\">");
                    out.println("                            <ul>");
                    out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
                    out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
                    out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
                    out.println("                                <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">View Review</a></li>");
                    out.println("                            </ul>");
                    out.println("                        </div>");
                }
                out.println("                        </li>");
                out.println("                        <li style=\"width:32%;\">");
                {
                    Accessory accessory = productHashmap.getAccessory(item.accessories.get(5));
                    out.println("                        <div class=\"accessory\">");
                    out.println("                            <ul>");
                    out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
                    out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
                    out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
                    out.println("                                <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">View Review</a></li>");
                    out.println("                            </ul>");
                    out.println("                        </div>");
                }
                out.println("                        </li>");
                out.println("                    </ul>");
                out.println("                </div>");
                out.println("                <div class=\"item\">");
                out.println("                    <ul>");
                out.println("                        <li style=\"width:32%;\">");
                {
                    Accessory accessory = productHashmap.getAccessory(item.accessories.get(6));
                    out.println("                        <div class=\"accessory\">");
                    out.println("                            <ul>");
                    out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
                    out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
                    out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
                    out.println("                                <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                    out.println("                                <li><a href=\"#\" class=\"button \">View Review</a></li>");
                    out.println("                            </ul>");
                    out.println("                        </div>");
                }
                out.println("                        </li>");
                out.println("                    </ul>");
                out.println("                </div>");
                out.println("            </div>");
                //<!-- 轮播（Carousel）导航 -->
                out.println("            <a class=\"carousel-control left\" href=\"#myCarousel\" data-slide=\"prev\">");
                out.println("                <span class=\"glyphicon glyphicon-chevron-left\"></span>");
                out.println("                <span class=\"sr-only\">Previous</span>");
                out.println("            </a>");
                out.println("            <a class=\"carousel-control right\" href=\"#myCarousel\" data-slide=\"next\">");
                out.println("                <span class=\"glyphicon glyphicon-chevron-right\"></span>");
                out.println("                <span class=\"sr-only\">Next</span>");
                out.println("            </a>");
                out.println("        </div>");
            }*/
            showAccessories(item, out, productHashmap);

            //out.println("        <div class=\"accessory-list\">");
            //out.println("            <ul>");

            //for (String accessory: item.accessories)
            /*{
                if(null == accessory.trim() || accessory.trim().length() == 0) continue;
                out.println("            <div class=\"accessory\">");
                out.println("                <ul>");
                out.println("                    <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory + ".png\" alt=" + accessory + "/></a></li>");
                out.println("                    <li class=\"accessory-name\">" + accessory + "</li>");
                out.println("                    <li><a href=\"CartServlet?id=" + "#" + "\" class=\"button \">Buy Now</a></li>");
                out.println("                    <li><a href=\"#\" class=\"button \">Write Review</a></li>");
                out.println("                    <li><a href=\"#\" class=\"button \">View Review</a></li>");
                out.println("                </ul>");
                out.println("            </div>");
            }*/
            
            //out.println("            </ul>");
            //out.println("        </div>");

        }else if(null != name){
            accessory = productHashmap.getAccessory(name);
            out.println("        <div class=\"product\">");
            out.println("            <ul>");
            out.println("                <li><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></li>");
            out.println("                <li>");
            out.println("                    <ul>");
            out.println("                        <li><b>accessory #" + accessory.id + "</b></li>");
            out.println("                        <li><b>$ " + accessory.price + "</b></li>");
            out.println("                        <li>name:  " + accessory.name);
            out.println("                        <li>number:" + cartAcc.getAccessoryNum(accessory));
            out.println("                    </ul>");
            out.println("                </li>");
            out.println("            </ul>");
            out.println("        </div>");

            out.println("        <div class=\"clear\"></div>");
        }else{// directly click cart
            for(int i=0; i<itemsOrdered.size(); i++) {
                ItemOrder order = (ItemOrder)itemsOrdered.get(i);
                item = order.getItem();
                //if (order.getItem().getId().equals(item.getId()))
                {
                    out.println("        <div class=\"product\">");
                    out.println("            <ul>");
                    out.println("                <li><a href=\"PhoneOpenProductServlet?id=" + id + "\"><img class=\"content-image\" src=\"images/" + item.image + ".png\" alt=" + item.name + "/></a></li>");
                    out.println("                <li>");
                    out.println("                    <ul>");
                    out.println("                        <li><b>item #" + item.id + "</b></li>");
                    out.println("                        <li><b>$ " + item.price + "</b></li>");
                    out.println("                        <li>name:  " + item.name);
                    out.println("                        <li>color: " + item.color);
                    out.println("                        <li>number:" + cart.getItemNum(item));
                    out.println("                        <li><a href=\"RemoveProductServlet?id=" + item.id + "\" class=\"button\">Remove</a></li>");
                    out.println("                    </ul>");
                    out.println("                </li>");
                    out.println("            </ul>");
                    out.println("        </div>");
                  
                }

                //if(i == itemsOrdered.size() - 1){
                //    out.println("        <div class=\"clear\"></div>");
                //    showAccessories(item, out, productHashmap);
                //}
            }
            for(int i=0; i<accsOrdered.size(); i++) {
                AccessoryOrder order = (AccessoryOrder)accsOrdered.get(i);
                accessory = order.getAccessory();
                //if (order.getItem().getId().equals(item.getId()))
                {
                    out.println("        <div class=\"product\">");
                    out.println("            <ul>");
                    out.println("                <li><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></li>");
                    out.println("                <li>");
                    out.println("                    <ul>");
                    out.println("                        <li><b>accessory #" + accessory.id + "</b></li>");
                    out.println("                        <li><b>$ " + accessory.price + "</b></li>");
                    out.println("                        <li>name:  " + accessory.name);
                    out.println("                        <li>number:" + cartAcc.getAccessoryNum(accessory));
                    out.println("                        <li><a href=\"RemoveProductServlet?name=" + accessory.name + "\" class=\"button\">Remove</a></li>");
                    out.println("                    </ul>");
                    out.println("                </li>");
                    out.println("            </ul>");
                    out.println("        </div>");
                  
                }
            }
            out.println("        <div class=\"clear\"></div>");
            showAccessories(item, out, productHashmap);
        }
        out.println("</section>");

        //===========================================================================================================

        

        /*
		out.println("<li>");
            out.println("<h4>checkout</h4>");
            out.println("    <section class=\"cart-total\">");
            out.println("        <ul>");
            out.println("           <li>Total Value:</li>");
            out.println("           <li>Item Number:</li>");
            out.println("        </ul>");    
            out.println("    </section>");
            out.println("</li>");
        */

        out.println("    <div class=\"clear\"></div>");
    	out.println("    </div>");
        out.println("    <footer>");
        out.println("        <div class=\"footer-content\">");
        out.println("           <h4>My SmartPortables</h4>");
        out.println("            <ul>");
        if(null == username){
            out.println("            <li><a href=\"LoginServlet\">Login</a></li>");
            out.println("            <li><a href=\"CreateAccountServlet\">Create account</a></li>");
        }else{
            out.println("            <li><a href=\"ViewOrderServlet\">View order</a></li>");
            if(usertype.equalsIgnoreCase("Salesman")){
                out.println("        <li><a href=\"CreateAccountServlet\">Create Customer Account</a></li>");
                out.println("        <li><a href=\"#\">Manage orders</a></li>");
            }
            if(usertype.equalsIgnoreCase("Manager")){
                out.println("        <li><a href=\"ManagerFunctionServlet\">Manage Products</a></li>");
                out.println("        <li><a href=\"InventoryReportServlet\">Inventory Report</a></li>");
                out.println("        <li><a href=\"SalesReportServlet\">Sales Report</a></li>");
            }
            out.println("            <li><a href=\"LogoutServlet\">Log out</a></li>");
        }
        out.println("            </ul>");
        out.println("            <div class=\"clear\"></div>");
        out.println("        </div>");       
        out.println("    </footer>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    public void showAccessories(Item item, PrintWriter out, XmlSaveToHashmap productHashmap){
        if(item == null) return;
        out.println("        <div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" data-interval=\"2500\">");
        //<!-- 轮播（Carousel）指标 -->
        out.println("            <ol class=\"carousel-indicators\">");
        out.println("                <li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>");
        out.println("                <li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>");
        out.println("                <li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>");
        out.println("            </ol>");
        //<!-- 轮播（Carousel）项目 -->
        out.println("            <div class=\"carousel-inner bor_btm\">");
        out.println("                <div class=\"item active\">");
        out.println("                    <ul>");
        out.println("                        <li style=\"width:32%;\">");
        {
            Accessory accessory = productHashmap.getAccessory(item.accessories.get(0));
            out.println("                        <div class=\"accessory\">");
            out.println("                            <ul>");
            out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
            out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
            out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
            out.println("                                <li><a href=\"CartServlet?name=" + accessory.name + "\" class=\"button \">Buy Now</a></li>");
            out.println("                                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("                            </ul>");
            out.println("                        </div>");
        }
        out.println("                        </li>");
        out.println("                        <li style=\"width:32%;\">");
        {
            Accessory accessory = productHashmap.getAccessory(item.accessories.get(1));
            out.println("                        <div class=\"accessory\">");
            out.println("                            <ul>");
            out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
            out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
            out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
            out.println("                                <li><a href=\"CartServlet?name=" + accessory.name + "\" class=\"button \">Buy Now</a></li>");
            out.println("                                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("                            </ul>");
            out.println("                        </div>");
        }
        out.println("                        </li>");
        out.println("                        <li style=\"width:32%;\">");
        {
            Accessory accessory = productHashmap.getAccessory(item.accessories.get(2));
            out.println("                        <div class=\"accessory\">");
            out.println("                            <ul>");
            out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
            out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
            out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
            out.println("                                <li><a href=\"CartServlet?name=" + accessory.name + "\" class=\"button \">Buy Now</a></li>");
            out.println("                                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("                            </ul>");
            out.println("                        </div>");
        }
        out.println("                        </li>");
        out.println("                    </ul>");
        out.println("                </div>");
        out.println("                <div class=\"item\">");
        out.println("                    <ul>");
        out.println("                        <li style=\"width:32%;\">");
        {
            Accessory accessory = productHashmap.getAccessory(item.accessories.get(3));
            out.println("                        <div class=\"accessory\">");
            out.println("                            <ul>");
            out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
            out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
            out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
            out.println("                                <li><a href=\"CartServlet?name=" + accessory.name + "\" class=\"button \">Buy Now</a></li>");
            out.println("                                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("                            </ul>");
            out.println("                        </div>");
        }
        out.println("                        </li>");
        out.println("                        <li style=\"width:32%;\">");
        {
            Accessory accessory = productHashmap.getAccessory(item.accessories.get(4));
            out.println("                        <div class=\"accessory\">");
            out.println("                            <ul>");
            out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
            out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
            out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
            out.println("                                <li><a href=\"CartServlet?name=" + accessory.name + "\" class=\"button \">Buy Now</a></li>");
            out.println("                                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("                            </ul>");
            out.println("                        </div>");
        }
        out.println("                        </li>");
        out.println("                        <li style=\"width:32%;\">");
        {
            Accessory accessory = productHashmap.getAccessory(item.accessories.get(5));
            out.println("                        <div class=\"accessory\">");
            out.println("                            <ul>");
            out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
            out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
            out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
            out.println("                                <li><a href=\"CartServlet?name=" + accessory.name + "\" class=\"button \">Buy Now</a></li>");
            out.println("                                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("                            </ul>");
            out.println("                        </div>");
        }
        out.println("                        </li>");
        out.println("                    </ul>");
        out.println("                </div>");
        out.println("                <div class=\"item\">");
        out.println("                    <ul>");
        out.println("                        <li style=\"width:32%;\">");
        {
            Accessory accessory = productHashmap.getAccessory(item.accessories.get(6));
            out.println("                        <div class=\"accessory\">");
            out.println("                            <ul>");
            out.println("                                <li><a href=\"#\"><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></a></li>");
            out.println("                                <li class=\"accessory-name\">" + accessory.name + "</li>");
            out.println("                                <li class=\"accessory-name\">$ " + accessory.price + "</li>");
            out.println("                                <li><a href=\"CartServlet?name=" + accessory.name + "\" class=\"button \">Buy Now</a></li>");
            out.println("                                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("                            </ul>");
            out.println("                        </div>");
        }
        out.println("                        </li>");
        out.println("                    </ul>");
        out.println("                </div>");
        out.println("            </div>");
        //<!-- 轮播（Carousel）导航 -->
        out.println("            <a class=\"carousel-control left\" href=\"#myCarousel\" data-slide=\"prev\">");
        out.println("                <span class=\"glyphicon glyphicon-chevron-left\"></span>");
        out.println("                <span class=\"sr-only\">Previous</span>");
        out.println("            </a>");
        out.println("            <a class=\"carousel-control right\" href=\"#myCarousel\" data-slide=\"next\">");
        out.println("                <span class=\"glyphicon glyphicon-chevron-right\"></span>");
        out.println("                <span class=\"sr-only\">Next</span>");
        out.println("            </a>");
        out.println("        </div>");
    }

}



