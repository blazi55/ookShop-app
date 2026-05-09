import './App.css';
import React, {FC} from 'react';
import {ButtonNextPage} from './ButtonNextPage.tsx';

interface BuyPageProps {
}

export const BuyPage: FC<BuyPageProps> = (props: BuyPageProps) => {
    const target = "/";
    const fieldText = "Powrót";

    return (
        <>
            <ButtonNextPage target={target} fieldText={fieldText}/>
            <div className="block">

            </div>
        </>
    )
}