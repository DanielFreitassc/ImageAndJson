import { useState } from 'react'
import { FoodData } from './components/interface/FoodData';
import { Card } from './components/card';
import './App.css'

function App() {
  const data: FoodData[] = [];


  return (
    <>
      <h1>Cardápio</h1>
      <div className="car-grid"></div>
      {data.map(foodData => <Card price={foodData.price } title={foodData.title} image={foodData.image}/>)}
    </>
  )
}

export default App
