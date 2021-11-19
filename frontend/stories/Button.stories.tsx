import React from "react";
import {ComponentStory, ComponentMeta} from "@storybook/react";
import {Button} from "./Button";

export default {
  title: "B103/Button",
  component: Button,
  argTypes: {
    backgroundColor: {control: 'color' },
    borderRadius: {control: 'range' },
  },
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = (args) => {
  return (
    <>
      <Button {...args} />
    </>
  );
};

export const DefaultButton = Template.bind({});
DefaultButton.args = {
  label: "Scroll down",
  backgroundColor: "#FFD523",
  color: 'navy',
  borderRadius: 12,
  width: 150,
  height: 45,
  fontSize: 22,
  fontWeight: 400,
  border: 'none',
};

export const Buttons: React.VFC<{}> = () => {
  return (
    <>
      <Button
        backgroundColor="#FFD523"
        border="none"
        borderRadius={12}
        color="navy"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Scroll down"
        onClick={() => {}}
        width={150}
      />
      <Button
        backgroundColor="#2C2E43"
        border="none"
        borderRadius={12}
        color="#FFD523"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Update"
        onClick={() => {}}
        width={110}
      />
      <Button
        backgroundColor="#B2B1B9"
        border="none"
        borderRadius={12}
        color="#595260"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Open"
        onClick={() => {}}
        width={95}
      />
      <Button
        backgroundColor="#595260"
        border="none"
        borderRadius={12}
        color="#B2B1B9"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Open"
        onClick={() => {}}
        width={95}
      />
      <br></br>
      <br></br>
      <Button
        backgroundColor="white"
        border="2px solid #FFD523"
        borderRadius={12}
        color="#FFD523"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Scroll down"
        onClick={() => {}}
        width={150}
      />
      <Button
        backgroundColor="white"
        border="2px solid #2C2E43"
        borderRadius={12}
        color="#2C2E43"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Update"
        onClick={() => {}}
        width={110}
      />
      <Button
        backgroundColor="#B2B1B9"
        border="none"
        borderRadius={22}
        color="#595260"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Open"
        onClick={() => {}}
        width={95}
      />
      <Button
        backgroundColor="#595260"
        border="none"
        borderRadius={12}
        color="#B2B1B9"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="확인"
        onClick={() => {}}
        width={95}
      />
      <br></br>
      <br></br>
      <Button
        backgroundColor="#D7D7DD"
        border="none"
        borderRadius={12}
        color="white"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="disabled"
        onClick={() => {}}
        width={120}
      />
      <Button
        backgroundColor="white"
        border="2px solid #D7D7DD"
        borderRadius={12}
        color="#D7D7DD"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="disabled"
        onClick={() => {}}
        width={120}
      />
      <Button
        backgroundColor="#B2B1B9"
        border="none"
        borderRadius={22}
        color="#595260"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="Open"
        onClick={() => {}}
        width={95}
      />
      <Button
        backgroundColor="#595260"
        border="none"
        borderRadius={22}
        color="#FFD523"
        fontSize={22}
        fontWeight={400}
        height={45}
        label="확인"
        onClick={() => {}}
        width={95}
      />
    </>
  );
};
