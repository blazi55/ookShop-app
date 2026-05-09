import './App.css';
import React, {FC} from 'react';
import {Link} from 'react-router-dom';

interface ButtonNextPageProps {
    target: string,
    fieldText: string,
}

export const ButtonNextPage: FC<ButtonNextPageProps> = (props: ButtonNextPageProps) => {
    
    return (
        <div className="button_page">
            <Link to={props.target}>
                <div>{props.fieldText}</div>
            </Link>
        </div>
    )
}