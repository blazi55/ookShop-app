import './App.css';
import React, {FC, useState} from 'react';
import book from './book.jpg'
import {ButtonNextPage} from './ButtonNextPage.tsx';
import {Book} from './Book.tsx';

interface FirstPageProps {

}

export const FirstPage: FC<FirstPageProps> = (props: FirstPageProps) => {
    const target = "/buy";
    const fieldText = "Przejdz do koszyka";
    const[books, setBooks] = useState([
        { 
            id: 12341,
            image: book,
            title: "harry potter1"
        },
        { 
            id: 34122,
            image: book,
            title: "harry potter2"
        },
        { 
            id: 44444, 
            image: book,
            title: "harry potter3"
        },
        { 
            id: 54444, 
            image: book,
            title: "harry potter4"
        }
    ])

    return (
        <>
            <ButtonNextPage target={target} fieldText={fieldText}/>
            <div className="block">
                {books.map(book => (
                    <Book id={book.id} image={book.image} title={book.title}/>
                ))}
            </div>
        </>
    )
}
