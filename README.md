# Consumer-Electronics-Retailer-Web-Application

Implementation of a web application for online electronics retailer using Java. The main goal of this project is to build a servlet-based online shopping web application like Amazon, Target and etc. Features designed for three different roles, customer, salesman and manager.

Application Features

For user
1. Customer must be able to create an account online.
2. The customer must be able to place an order online, check the status of the order, or cancel the order.
3. The customer will pay by credit card.
4. Customer shall be able to shop online to buy one or multiple items in the same session from the SmartPortables online retailer.
5. In the same session, the customer must be able to add or remove items from the shopping cart.
6. When the customer chooses to check out: (1)The customer will enter personal information (Name, Address, Credit Card, etc.). (2) The customer will be provided with a confirmation number and a delivery date (2 weeks after the order date) that the customer can use to cancel an order at a later timer, though it must be 5 business days before the delivery date.
7. Customer must be able to submit product reviews.
8. Auto-complete searching using Ajax, once the customer start typing search keywords in the search box, a table of potential keyword-matches will be displayed.
9. Use a python script to connect to Twitter server to fetch current deal from BestBuy_Deals, and then the script will connect to MySql database to get list of products on sale. Write the deals fetched from server into a txt file and compare deals of products that matches the one in the local database, and display any two of them on homepage along with the link to the product.

For manager
1. Manager can add/ delete/ update products, reflected both in hashmap and MySql database.
2. Manager must be able to generate a table in the inventory link of all products and how many items of every product currently available in the store; list only product name, price, how many items of that product available.
3. Manager must be able to generate a Bar Chart in the inventory link that shows the product names and the total number of items available for every product.
4. Manager must be able to generate a table in the inventory link of all products currently on sale.
5. Manager must be able to generate a table in the inventory link of all products currently on sale that have manufacturer rebates.
6. Manager must be able to generate a table in the sales report link of all products sold and how many items of every product sold; list only the product name, price, number of items sold and total sales of every product sold.
7. Manager must be able to generate a bar chart in the sales report link that shows the product names and the total sales for every product.
8. Manager must be able to generate a table in sales report link of total daily sales transactions.

For salesman
1. Salesman can create customer account, add/ delete/ update customers' orders.

Web Features
1. The retailers sells different types of products.
2. Every product might have accessories that could be bought separately.
3. When a product is selected for a view, all accessories associated with that product must be displayed below the product
horizontally.
4. Retailer offers warranty that can be purchased by the customer for every console.
5. Some of the products may have retailer special-discounts.
6. Some of the products may have manufacturer rebates.
7. All accounts login information stored in MySql database.
8. All Customer transactions/orders stored in MySql database.
9. Product reviews must be stored in MongoDB database.
10. When the app-server starts up, the Products are first read into a hashmap from ProductCatalog.xml file and then stored in MySQL database.

***I didn't put the Twitter app credential key in the file list.
