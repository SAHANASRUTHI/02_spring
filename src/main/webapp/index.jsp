<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>

    <div class="container">
        <h2>Item Form</h2>
        <form action="javascript:void(0);" method="POST" id="itemForm">
            <div class="form-group">
                <label for="itemname">Item Name:</label>
                <input type="text" class="form-control" id="itemname" placeholder="Item Name" required>
            </div>
            <div class="form-group">
                <label for="expdate">Expiry Date:</label>
                <input type="date" class="form-control" id="expdate" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" class="form-control" id="price" placeholder="Price" required>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <input type="text" class="form-control" id="category" placeholder="Category" required>
            </div>
            <button type="submit" class="btn btn-primary" onclick="saveItem()">Save</button>
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
            <tbody id="items"></tbody>
        </table>
    </div>

    <script src="item.js"></script>
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', getItems);
    </script>
</body>

</html>
