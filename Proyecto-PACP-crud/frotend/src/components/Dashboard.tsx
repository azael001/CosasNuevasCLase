import { useSelector } from 'react-redux'
import { RootState } from '../store/index'

import {useNavigate } from 'react-router-dom'
import React from "react";

import Box from "@mui/material/Box";

import Grid from "@mui/material/Grid2";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";

import { useState } from "react";


function Dashboard() {
  const userData = useSelector((state: RootState) => state.authenticator)
  const navigate = useNavigate()
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
   const [open, setOpen] = React.useState(false);    
    const handleSubmit = (e:any) => {
     //Para que no mande el formulario, sino que haga lo que yo le diga
     e.preventDefault();
     setOpen(true);
     console.log(item)
   };



  return (
   
        <Box component="form" onSubmit={handleSubmit} sx={{width:1}}>
          <Grid container sx={{mt:2}} >
            
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                required
                label="Nombre"
                variant="outlined"
                fullWidth
              />
            </Grid>
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                label="Marca"
                variant="outlined"
                fullWidth
                required 
              />
            </Grid>
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                label="Tipo"
                variant="outlined"
                fullWidth
                required 
              />
            </Grid>
            <Grid size={{xs:6,md:4 ,xl:3}}>
              <TextField
                required
                label="precio"
                variant="outlined"
                type="number"
                fullWidth
              />
             </Grid>
        </Grid>
        </Box>
  );
}

export default Dashboard