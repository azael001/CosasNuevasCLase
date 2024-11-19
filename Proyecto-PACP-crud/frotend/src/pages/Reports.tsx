import MenuAll from "../components/MenuAll"
import {useNavigate } from 'react-router-dom'
import { useSelector } from 'react-redux'
import { RootState } from '../store/index'
import { useEffect } from 'react';
import { ExportCsv, ExportPdf } from "@material-table/exporters";
import MaterialTable from "@material-table/core";
import { Column } from "@material-table/core";


function Reports() {

  const userData = useSelector((state: RootState) => state.authenticator)
  const navigate = useNavigate()
  const isLoggedin = userData.isAutenticated
    useEffect(() => {
    if (!isLoggedin) {
    navigate('/')
    }
    
    }, [isLoggedin, navigate])


    interface IPerson {
      firstName: string;
      lastName: string;
      birthYear: number;
     }
     //Creación de los datos de prueba:
     // --> definición de las columnas de la tabla
      //Para cada elemento que queremos mostrar tendremos el title y el field
      //Será un array del tipo Column (tipo de material-table-core que importamos arriba) cuyos
      //elementos son del tipo IPerson que definimos nosotros.
      //El title contendrá el título de la columna de la tabla que es lo que veremos en la interfaz
      //El field contendrá el nombre que le damos a ese campo en la tabla
      //Por ejemplo: tendremos una columna con el title Nombre cuyo campo se llamará firstName
      //Podemos indicar también el type y decir que es numérico, como en el caso del año nacimiento
      const col: Array<Column<IPerson>> = [
      { title: "Nombre", field: "firstName"},
      { title: "Apellido", field: "lastName" },
      { title: "Año nacimiento", field: "birthYear", type: "numeric" }
      ];
     
     // --> definición de los datos de la tabla
      //Datos que se van a mostrar en la tabla para el informe: aquí hemos puesto tres filas de la tabla,
      //pero podemos poner tantas como queramos o necesitemos
      //En una aplicación real estos datos vendrían de una consulta a la base de datos
      const tableData = [
     { firstName: "Jorge", lastName: "Rodríguez", birthYear: 1987 },
     { firstName: "Lucrecia", lastName: "Pérez", birthYear: 2000 },
     { firstName: "Rocío", lastName: "Pérez", birthYear: 2000 }
      ];

    return (
        <>
        <MenuAll></MenuAll>
        <MaterialTable columns={col} data={tableData} />

        </>

    )
  }
export default Reports