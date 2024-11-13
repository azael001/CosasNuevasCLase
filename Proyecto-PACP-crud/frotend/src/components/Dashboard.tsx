
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid2";
import TextField from "@mui/material/TextField";
import { useState } from "react";
import { Button } from '@mui/material';
import TableContainer from "@mui/material/TableContainer";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
function Dashboard() {
 

  interface itemtype {
    id?: number
    nombre: string
    marca: string
    tipo: string
    precio: number
   }
   const itemInitialState: itemtype = {
    nombre: ' ',
    marca: ' ',
    tipo: ' ',
    precio: 0
   }
   const [item, setItem] = useState(itemInitialState)   
   const precio = parseFloat(item.precio.toString());
   const [tableData, setTableData] = useState([])

    const handleSubmit = (e:any) => {
      e.preventDefault()
     //Para que no mande el formulario, sino que haga lo que yo le diga
     console.log(item)
     fetch(`http://localhost:3030/addItem?nombre=${item.nombre}&marca=${item.marca}&tipo=${item.tipo}&precio=${item.precio}`)
     .then(response => response.json())
     .then (response => {
     console.log(response)
     if (response > 0){
       alert('Datos insertados con éxito')
     } else{
       alert('No se han insertado bien')
       }
      })
   };


   
   const handleChangeName = (e:any) => {
    setItem({
      ...item,
      nombre: e.target.value,
    });
  };
  const handleChangeMarca = (e:any) => {
    setItem({
      ...item,
      marca: e.target.value,
    });
  };

  const handleChangeTipo = (e:any) => {
    setItem({
      ...item,
      tipo: e.target.value,
    });
  };

  const handleChangePrecio = (e:any) => {
    setItem({
      ...item,
      precio: parseFloat(e.target.value),
    });
  };
  const handleDeleteItem =(e:any)=>{
    fetch(`http://localhost:3030/deleteItem?id=${item.id}`)
    .then(response => response.json())
    .then (response => {
    console.log(response)
    if (response!== 0){
      alert('eliminado correctamente')
    } 
  else{
    alert('No se ha podido eliminar')
  } }) };

  fetch(`http://localhost:3030/getData`)
  .then(response => response.json())
  .then (response => {
  console.log(response)
});




  return (
   
        <Box component="form" sx={{width:1}} onSubmit={handleSubmit}>
          <Grid container sx={{mt:2, justifyContent:'center'}} >
            
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                required
                label="Nombre"
                variant="outlined"
                fullWidth
                value={item.nombre}
                onChange={handleChangeName}
              />
            </Grid>
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                label="Marca"
                variant="outlined"
                fullWidth
                required 
                value={item.marca}
                onChange={handleChangeMarca}
              />
            </Grid>
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                label="Tipo"
                variant="outlined"
                fullWidth
                required 
                value={item.tipo}
                onChange={handleChangeTipo}
              />
            </Grid>
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                required
                label="precio"
                variant="outlined"
                type="number"
                fullWidth
                value={item.precio}
                onChange={handleChangePrecio}
              />
             </Grid>
             <Grid size={{xs:6,md:4 ,xl:12 }} ></Grid>
             <Button variant='contained' sx={{mt:2,width:0.6}} type='submit' >+Insertar Datos</Button>
        </Grid>
        <TableContainer>
          <Table aria-label="coleccion">
          <TableHead>
          <TableRow>
          <TableCell></TableCell>
          <TableCell>Nombre</TableCell>
          <TableCell>Marca</TableCell>
          <TableCell>Tipo</TableCell>
          <TableCell>Precio</TableCell>
          </TableRow>
          </TableHead>
          <TableBody>
          {tableData.map((row: itemtype) => (
          <TableRow key={row.id}>
          <TableCell>
          <Button onClick={() => handleDeleteItem(row)}>
          <DeleteForeverIcon />
          </Button>
         </TableCell>
          <TableCell>{row.nombre}</TableCell>
          <TableCell>{row.marca}</TableCell>
          <TableCell>{row.tipo}</TableCell>
          <TableCell>{row.precio}</TableCell>
          </TableRow>))}
          </TableBody>
          </Table>
          </TableContainer>





        
        </Box>
  );
}

export default Dashboard