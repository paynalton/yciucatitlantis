package com.onurisys.yciucatitlantis.alertas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper{
	String sqlCreate = "CREATE TABLE Alertas (id VARCHAR(100),title TEXT,updated DATETIME,identifier VARCHAR(100),sender VARCHAR(100),sent DATETIME,status VARCHAR(100),msgtype VARCHAR(100),scope VARCHAR(100),code INT,language VARCHAR(100),category VARCHAR(100),event TEXT,responsetype VARCHAR(100),urgency VARCHAR(100),severity VARCHAR(100),certainty VARCHAR(100),effective DATETIME,expires DATETIME,sendername TEXT,headline TEXT,description TEXT,web VARCHAR(100),contact VARCHAR(100),parameters TEXT,area TEXT,nelat DOUBLE,nelon DOUBLE,swlat DOUBLE,swlon DOUBLE)";
	public DBhelper(Context contexto, String nombre,
            CursorFactory factory, int version) {
		super(contexto, nombre, factory, version);
		}
	
	 @Override
	    public void onCreate(SQLiteDatabase db) {
	        //Se ejecuta la sentencia SQL de creación de la tabla
	        db.execSQL(sqlCreate);
	    }
	 @Override
	    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
	        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
	        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
	        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
	        //      a la nueva, por lo que este método debería ser más elaborado.
	 
	        //Se elimina la versión anterior de la tabla
	        db.execSQL("DROP TABLE IF EXISTS Alertas");
	 
	        //Se crea la nueva versión de la tabla
	        db.execSQL(sqlCreate);
	    }
}
