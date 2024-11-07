import { useSelector } from 'react-redux'
import { RootState } from '../store/index'
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid2'

import MenuAll from '../components/MenuAll'
function Home() {
  // Almacenamos en la variable userData lo que obtenemos del store usando el hook useSelector
  const userData = useSelector((state: RootState) => state.authenticator)
  // Comprobamos por la consola qué obtenemos del store
  return (
    <>
    <MenuAll/>
    <Grid container  sx={{ justifyContent: 'center', alignItems: 'center' }}>
      <Typography variant='h2' color='primary'> Home de Pablo Azael: Soy el usuario {userData.userName} y tengo el rol de {userData.userRol}</Typography>
      </Grid>
    </>
  )
}


export default Home