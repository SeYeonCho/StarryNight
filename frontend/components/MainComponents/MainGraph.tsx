import React from "react";
import { Bar, BarChart, CartesianGrid, Cell, Customized, LabelList, Legend, Tooltip, XAxis, YAxis } from "recharts";
import styles from '../../styles/MainGraph.module.scss';
import { FaMedal } from "react-icons/fa";
const color = [
    'gold',
    'silver',
    '#CD7F32'
]

const grade = [
    '1st',
    '2nd',
    '3rd'
];

const CustomizedYAxis = (props) => {
    //console.log(props)
    const { x, y, index } = props;
    
    const filteredProps = props;

    delete filteredProps['tickformatter']
    delete filteredProps['visibletickscount']
    delete filteredProps['verticalanchor']

    return (
        <>
            {<FaMedal x={x - 27} y={y - 27}
                style={{
                    fill: `${color[index]}`,
                    fontSize: "30px"
                }}
            />}
    
            <text fill={`${color[index]}`} x={x-23} y={y}>
                
                <tspan dy="1.3em">{props.payload.value}</tspan>
            </text>
    
            
        </>
    );
}
let xAxis = ['매우 낮음', '낮음', '중간', '높음', '매우 높음'];
const MainGraph = ({ data }) => {
    
    
    if (data) {

        const parsing = (e) => {
            return parseInt(e.traffic.slice(0, e.traffic.length - 1).replace(",", ""))/1000-15;
        }


        data = data.map((e) => {
            return {
                "name": e.title,
                "traffic": parsing(e)
            }
        });

        data.sort((e1, e2) => {
            return e2.traffic - e1.traffic;
        })

        data = data.map((e,idx) => {
            return {
                ...e,
                traffic: 3 - idx,
                grade: grade[idx]
            }
        });

    }

    return (
        <>
            {data ? 
                
                <BarChart width={730} layout="vertical" height={250} data={data.slice(0,3)}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis type="number" dataKey="traffic" tickFormatter={(e,index) => {
                        
                        return xAxis[index];
                    }}/>
                    <YAxis
                        type="category"
                        dataKey="grade"
                        tick={<CustomizedYAxis />}
                    />
                    <Tooltip
                        position={{
                            x: 500, y: 150
                        }}
                        formatter={(value, name, props) => {
                            const { payload } = props;
                            return [payload.name, payload.grade]
                        }}
                        cursor={false}
                    />
                    <Bar dataKey="traffic" >
                    {
                        data.map((entry, index) => (
                            <Cell key={`cell-${index}`} stroke={color[index]} fill={"white"}  strokeWidth={1}>
                                
                            </Cell>
                        ))
                        }
                        
                        <LabelList dataKey="name" position="center"/>
                    </Bar>
                </BarChart>
                :
                <></>
            }
        </>
    );

}

export default MainGraph;

