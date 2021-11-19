import React from 'react';
import styled from 'styled-components';

const CheckboxDiv = styled.div`
  display: inline-flex;
  cursor: pointer;
  position: relative;

`;

interface CheckboxProps {
  /**
   * checkbox id 설정
   */
  id?: string;

  /**
   * checkbox name 설정
   */
  name?: string;

  /**
   * checked 된 상태 표시
   */
  checked?: boolean;

  /**
   * label이 가리키는 대상 설정
   */
  htmlFor?: string;

  /**
   * label 이름 설정
   */
  labelName?: string;

  /**
   * border 설정
   */
    border?: string;
  /**
   * checkbox size 
   */
    size?: number;
}

export const Checkbox = ({
  id,
  name,
  checked,
  htmlFor,
  labelName,
    border,
  size,
  ...props
}: CheckboxProps) => {
  return (
    <CheckboxDiv>
      <input
        type="checkbox"
        id={id}
        name={name}
        checked={checked}
        style={{
            border,
            width: size,
            height: size
        }}
      />
          <label htmlFor={htmlFor}
              style={{
                fontSize: size
          }}>{labelName}</label>
    </CheckboxDiv>
  );
};
