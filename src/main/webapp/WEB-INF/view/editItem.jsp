<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Edit Item</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        </head>

        <body>
            <div class="container">
                <h2>Edit Item</h2>
                <form action="updateItem" method="POST">
                    <div class="form-group">
                        <label for="itemId">Item ID:</label>
                        <input type="text" class="form-control" id="itemId" name="itemId" value="${item.itemId}"
                            readonly>
                    </div>
                    <div class="form-group">
                        <label for="itemname">Item Name:</label>
                        <input type="text" class="form-control" id="itemname" name="itemName" value="${item.itemName}"
                            required>
                    </div>
                    <div class="form-group">
                        <label for="expdate">Expiry Date:</label>
                        <input type="date" class="form-control" id="expdate" name="expDate" value="${item.expDate}"
                            required>
                    </div>
                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input type="number" class="form-control" id="price" name="price" value="${item.price}"
                            required>
                    </div>
                    <div class="form-group">
                        <label for="category">Category:</label>
                        <input type="text" class="form-control" id="category" name="category" value="${item.category}"
                            required>
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form>


                <h2>Item List</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Expiry Date</th>
                            <th>Price</th>
                            <th>Category</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                <td>${item.itemId}</td>
                                <td>${item.itemName}</td>
                                <td>${item.expDate}</td>
                                <td>${item.price}</td>
                                <td>${item.category}</td>
                                <td>
                                    <form action="deleteItem" method="POST">
                                        <input type="hidden" name="itemId" value="${item.itemId}" />
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="editItem" method="GET">
                                        <input type="hidden" name="itemId" value="${item.itemId}" />
                                        <button type="submit" class="btn btn-primary">Edit</button>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </body>

        </html>