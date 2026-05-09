import './App.css';
import React, {FC, useState} from 'react';
import {useNavigate} from 'react-router-dom';

interface BookProps {
    id: number,
    image: string,
    title: string
}

export const Book: FC<BookProps> = (props: BookProps) => {
    const[count, setCount] = useState(0);
    
    const showCount = () => {
        if(count > 0) {
            return 'Ilość sztuk: ' + count;
        } 
        if(count < 0) {
            setCount(0);
        }
        return 'Ilość sztuk: ';
    }

    let navigate = useNavigate();
    const routeChange = () => {
        if(count > 0) {
            let path = `/buy`; 
            navigate(path);
        }
        if (count <= 0) {
            alert('Wybierz ilość książek :D')
        }
    }

    return (
        <>
        <div className="book">
            <div className="title_book">
                {props.title}
                <div>
                    {showCount()}
                </div>
            </div>
            <div className="photo">
                {/* <img src={props.image} alt="book"/>; */}
            </div>
            <div className="panel">
                <button className="button_buy" onClick={routeChange}>Buy</button>
                <button className="button_buy_count_plus" onClick={() => setCount(count + 1)}>+</button>
                <button className="button_buy_count_minus" onClick={() => setCount(count - 1)}>-</button>
            </div>
        </div>
        </>
    )
}
