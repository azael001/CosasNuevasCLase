import { useSelector } from 'react-redux'
import { RootState } from '../store/index'
import { authActions } from '../store/authSlice';
import {useNavigate } from 'react-router-dom'
import { useDispatch} from 'react-redux'
import Button from '@mui/material/Button'
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import HomeIcon from '@mui/icons-material/Home';
import HelpIcon from '@mui/icons-material/Help';
import InsertDriveFileIcon from '@mui/icons-material/InsertDriveFile';


function MenuAll() {
    const userData = useSelector((state: RootState) => state.authenticator)
    console.log(userData)
    const dispatch = useDispatch()
    const navigate = useNavigate()
     const [open, setOpen] = React.useState(false);
    const toggleDrawer = (newOpen: boolean) => () => {
      setOpen(newOpen);
      };
    
    
      const DrawerList = (
        <Box sx={{ width: 250 }} role="presentation" onClick={toggleDrawer(false)}>
          <List >
              <ListItem >
                <ListItemButton>
                  <HomeIcon sx={{mr:2}}></HomeIcon>
                  <ListItemText>Home</ListItemText> 
                </ListItemButton>
              </ListItem>
          </List>
          <Divider />
          <List>
              <ListItem >
                <ListItemButton>
                  <InsertDriveFileIcon sx={{mr:2}}></InsertDriveFileIcon>
                  <ListItemText>Informes</ListItemText> 
                </ListItemButton>
              </ListItem>
          </List>
          <Divider />
          <List>
              <ListItem >
                <ListItemButton>
                  <HelpIcon sx={{mr:2}}></HelpIcon>
                  <ListItemText>Ayuda</ListItemText> 
                </ListItemButton>
              </ListItem>
          </List>
          <Divider />
          <List>
              <ListItem >
                <ListItemButton>
                  <ExitToAppIcon sx={{mr:2}}></ExitToAppIcon>
                  <ListItemText>Salir</ListItemText> 
                </ListItemButton>
              </ListItem>
          </List>
          <Divider />
        </Box>
      );

  return (
    <Box sx={{ flexGrow: 1 }}>
    <AppBar position="static">
      <Toolbar>
        <IconButton
          size="large"
          edge="start"
          color="inherit"
          aria-label="menu"
          sx={{ mr: 2 }}
          onClick={toggleDrawer(true)}
        >
          <MenuIcon />
        </IconButton>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          News
        </Typography>
        <IconButton >
          <AccountBoxIcon fontSize='large' style={{color:'white'}}>
            
          </AccountBoxIcon>
        </IconButton>
      </Toolbar>
      <Drawer open={open} onClose={toggleDrawer(false)}>
        {DrawerList}
      </Drawer>
    </AppBar>
  </Box>
);
}
  

export default MenuAll