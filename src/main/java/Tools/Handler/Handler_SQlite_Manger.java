package Tools.Handler;

import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.sql.*;

public class Handler_SQlite_Manger {

    private static Connection connect;
    private static boolean errored = false;

    public Handler_SQlite_Manger(LABMATT_SERVER_CONTROLLER plugin)
    {
        try
        {
            if(connect == null) {
                getConnection();
            }

            Statement state = connect.createStatement();
            ResultSet res = state.executeQuery("SELECT fname, firstName FROM user");
        } catch (Exception e)
        {
            if(!errored)
            {
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error loading SQLite database! Handler cannot work! Error: " + e);
                errored = true;
            }
        }

    }

    //Gets connection the SQLite database
    private void getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");

        connect = DriverManager.getConnection("jdbc:sqlite:test");

        checkHandlerDB();
    }


    //Checks if the databases are made. if not create them.
    private void checkHandlerDB() throws SQLException {

        ResultSet resultSet = connect.getMetaData().getCatalogs();
        String databaseName = "";

        while(resultSet.next())
        {
            databaseName = resultSet.getString(1);
        }
       if(!databaseName.equals("blocks"))
       {
           Statement statement = connect.createStatement();
           statement.execute("CREATE TABLE IF NOT EXISTS blocks (LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255));");
       }

    }
}
