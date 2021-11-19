import React from "react";
import "./button.css";

interface ButtonProps {
  /**
   * 배경색 설정
   */
  backgroundColor?: string;

  /**
   * 폰트 색상
   */
  color?: string;

  /**
   * 버튼 내부 설명
   */
  label: string;
  /**
   * 버튼 테두리
   */
  borderRadius?: number;

  /**
   * width 설정
   */
  width?: number;

  /**
   * height 설정
   */
  height?: number;

  /**
   * 폰트 사이즈 설정
   */
  fontSize?: number;

  /**
   * 폰트 굵기 설정
   */
  fontWeight?: number;

  /**
   * border 설정
   */
  border?: string;
  /**
   * Optional click handler
   */
  onClick?: () => void;
}

/**
 * Primary UI component for user interaction
 */
export const Button = ({
  backgroundColor,
  color,
  borderRadius,
  label,
  width,
  height,
  fontSize,
  fontWeight,
  border,
  ...props
}: ButtonProps) => {
  return (
    <button
      type="button"
      // className={['storybook-button', `storybook-button--${size}`, mode].join(
      //     " ",
      // )}
      style={{
        backgroundColor,
        color,
        width: `${width}px`,
        height: `${height}px`,
        borderRadius: `${borderRadius}px`,
        fontSize,
        fontWeight,
        border,
      }}
      {...props}
    >
      {label}
    </button>
  );
};
