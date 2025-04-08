import { useState } from 'react'

import './App.css'
import { BrowserRouter, Routes } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import { Route } from 'react-router-dom'

function App() {


  return (
    <>
     <BrowserRouter>
      <Routes>
         <Route path="/" element={<LandingPage />} /> 
         <Route path="/about" element={<AboutPage />} /> 
       z</Routes> 
     </BrowserRouter>
    </>
  )
}

export default App
