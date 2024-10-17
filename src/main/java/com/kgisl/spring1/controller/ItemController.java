package com.kgisl.spring1.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@RequestMapping("/item")
@Controller

public class ItemController {

    String jdbcURL, jdbcUsername, jdbcPassword;

    ItemController() {
        jdbcURL = "jdbc:mysql://localhost:3306/itemdatabase";
        jdbcUsername = "root";
        jdbcPassword = "";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getItem(Model model) {
        System.out.println("doGet called");
        List<Item> itemList = new ArrayList<>();

        try {
            Connection jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            String sql = "SELECT * FROM ItemDetails";
            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Item p = new Item();
                p.setItemId(resultSet.getInt(1));
                p.setItemName(resultSet.getString(2));
                p.setExpDate(resultSet.getDate(3));
                p.setPrice(resultSet.getInt(4));
                p.setCategory(resultSet.getString(5));
                itemList.add(p);
            }

            resultSet.close();
            statement.close();
            jdbcConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("items", itemList);
        return "index"; // This will look for index.jsp in src/main/webapp/
    }


    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(@RequestParam("itemName") String itemName,
            @RequestParam("expDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date expDate,
            @RequestParam("price") int price,
            @RequestParam("category") String category,
            Model model) {
        try (Connection jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                PreparedStatement statement = jdbcConnection.prepareStatement(
                        "INSERT INTO ItemDetails (ItemName, ExpDate, Price, Category) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, itemName);
            java.sql.Date sqlExpDate = new java.sql.Date(expDate.getTime());
            statement.setDate(2, sqlExpDate);
            statement.setInt(3, price);
            statement.setString(4, category);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new item was inserted successfully!");
            }

          

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect to the GET endpoint to fetch and display the updated list
        return "redirect:/item/";
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
    public String deleteCustomer(@RequestParam("itemId") int itemId) {
        try {
            Connection jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            String sql = "DELETE FROM ItemDetails WHERE ItemId = ?";
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, itemId);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Item with ID " + itemId + " was deleted successfully!");
            }

            statement.close();
            jdbcConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/item/"; // Redirect back to the item list after deletion
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.GET)
    public String editItem(@RequestParam("itemId") int itemId, Model model) {
        try (Connection jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                PreparedStatement statement = jdbcConnection
                        .prepareStatement("SELECT * FROM ItemDetails WHERE ItemId = ?")) {

            statement.setInt(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Item item = new Item();
                    item.setItemId(resultSet.getInt("ItemId"));
                    item.setItemName(resultSet.getString("ItemName"));
                    item.setExpDate(resultSet.getDate("ExpDate"));
                    item.setPrice(resultSet.getInt("Price"));
                    item.setCategory(resultSet.getString("Category"));
                    model.addAttribute("item", item);
                }
            }

            // Fetch all items for the table
            List<Item> itemList = new ArrayList<>();
            try (Statement stmt = jdbcConnection.createStatement();
                    ResultSet resultSet = stmt.executeQuery("SELECT * FROM ItemDetails")) {
                while (resultSet.next()) {
                    Item i = new Item();
                    i.setItemId(resultSet.getInt("ItemId"));
                    i.setItemName(resultSet.getString("ItemName"));
                    i.setExpDate(resultSet.getDate("ExpDate"));
                    i.setPrice(resultSet.getInt("Price"));
                    i.setCategory(resultSet.getString("Category"));
                    itemList.add(i);
                }
            }

            model.addAttribute("items", itemList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "editItem"; // This returns the view name
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public String updateItem(@RequestParam("itemId") int itemId,
            @RequestParam("itemName") String itemName,
            @RequestParam("expDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date expDate,
            @RequestParam("price") int price,
            @RequestParam("category") String category) {
        try {
            Connection jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            String sql = "UPDATE ItemDetails SET ItemName = ?, ExpDate = ?, Price = ?, Category = ? WHERE ItemId = ?";
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);

            statement.setString(1, itemName);
            java.sql.Date sqlExpDate = new java.sql.Date(expDate.getTime());
            statement.setDate(2, sqlExpDate);
            statement.setInt(3, price);
            statement.setString(4, category);
            statement.setInt(5, itemId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Item with ID " + itemId + " was updated successfully!");
            }

            statement.close();
            jdbcConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/item/"; // Redirect back to the item list after updating
    }

}
