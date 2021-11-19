import React from "react";
import {ComponentStory, ComponentMeta} from '@storybook/react';
import {Checkbox} from './Checkbox';

export default {
  title: "B103/Checkbox",
  component: Checkbox,
  argTypes: {
    // backgroundColor: {control: 'color' },
    // borderRadius: {control: 'range' },
  },
} as ComponentMeta<typeof Checkbox>;

const Template: ComponentStory<typeof Checkbox> = (args) => {
  return (
    <>
      <Checkbox {...args} />
    </>
  );
};

export const DefaultCheckbox = Template.bind({});
DefaultCheckbox.args = {
  id: 'disabled',
  name: 'disabled',
  checked: true,
  htmlFor: 'disabled',
  labelName: "disabled",
  border: '15px solid #2C2E43',
  size: 25
};

export const Checkboxs: React.VFC<{}> = () => {
  return (
    <>
      <Checkbox
        checked={false}
        htmlFor="able"
        id="able"
        labelName="able"
        name="able"
      />
      <Checkbox checked htmlFor="체크" id="체크" labelName="체크" name="체크" />
      <br></br>
      <br></br>
      <Checkbox
        checked
        htmlFor="채우기"
        id="채우기"
        labelName="채우기"
        name="채우기"
      />
      <Checkbox
        checked
        htmlFor="박스체크"
        id="박스체크"
        labelName="박스체크"
        name="박스체크"
      />
    </>
  );
};
