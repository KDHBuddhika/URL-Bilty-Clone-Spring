import { useState } from 'react'

import './App.css'
import { BrowserRouter as Router, Routes } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import { Route } from 'react-router-dom'
import Navbar from './components/Navbar'
import Footer from './components/Footer'
import RegisterPage from './components/RegisterPage'

function App() {


  return (
    <>
    
     <Router>
      {/* <Navbar/> */}
          <Routes>
              <Route path="/" element={<LandingPage />} /> 
              <Route path="/about" element={<AboutPage />} /> 
              <Route path="/register" element={<RegisterPage />} /> 
          </Routes> 

        <Footer/>
     </Router>
    
    </>
  )
}

export default App
