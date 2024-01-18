package com.example.myapplication

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class DatabaseManager {
    fun testerConnexion(urlConnexion: String): Boolean {
        return try {
            Class.forName("com.mysql.jdbc.Driver")
            val connexion: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/android", "root","root")
            val statement: Statement = connexion.createStatement()
            connexion.close()

            println("Connexion réussie.")
            true
        } catch (e: SQLException) {
            println("Erreur lors de la connexion à la base de données: ${e.message}")
            false
        } catch (e: ClassNotFoundException) {
            println("Erreur lors du chargement du pilote JDBC: ${e.message}")
            false
        }
    }
}