import { useState } from 'react'
import { FoodData } from './components/interface/FoodData';
import { Card } from './components/card';
import { useFoodData } from './components/hooks/useFoodData';
import './App.css'
import { CreateModal } from './components/create-model/create-modal';

function App() {
  const {data} = useFoodData();
  const [isModalOpen, setIsModalOpen]= useState(false);

  const handleOpenModal = () => {
    setIsModalOpen(prev => !prev)
  }

  return (
    <div className="container">
      <h1>Cardápio</h1>
      <div className="card-grid">
        {data?.map(foodData => 
          <Card
            price={foodData.price} 
            title={foodData.title} 
            image={foodData.image}
          />
        )}
      </div>
        {isModalOpen && <CreateModal closeModal={handleOpenModal}/>}
        <button onClick={handleOpenModal}>novo</button>
      
    </div>
  )
}

export default App
