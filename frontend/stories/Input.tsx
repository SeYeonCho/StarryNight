import React from 'react';

// import { Input } from './Input';
import './input.css';

interface InputProps {
  size?: 'small' | 'medium' | 'large';
  type: string,
  placeholder?: string;
  onClick?: () => void;
}

export const Input = ({
  size = 'medium',
  type,
  placeholder,
  ...props
}: InputProps) => {

  return(
    <input
      placeholder={ placeholder }
      type= { type }
      className={['normal-input', `normal-input--${size}`,].join(' ')}
      {...props}
    >
    </input>
  )
};