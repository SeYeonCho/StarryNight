import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';

import { Input } from './Input';

export default {
  title: 'Example/Input',
  component: Input,
} as ComponentMeta<typeof Input>;

const Template: ComponentStory<typeof Input> = (args) => <Input {...args} />;

export const Large = Template.bind({});
Large.args = {
  size: 'large',
  type: 'text',
  placeholder: '내용을 입력하세요.',
};

export const Medium = Template.bind({});
Medium.args = {
  size: 'medium',
  type: 'text',
  placeholder: '내용을 입력하세요.',
};

export const Small = Template.bind({});
Small.args = {
  size: 'small',
  type: 'text',
  placeholder: '내용을 입력하세요.',
};

