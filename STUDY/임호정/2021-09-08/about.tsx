import type { NextPage } from 'next';
import Head from 'next/head';
import Image from 'next/image';
import styles from '../styles/Home.module.css';
import { InferGetStaticPropsType } from 'next';

type Data = {
  resultCode: string;
  resultMsg: string;
  items: Item;
  totalCount: number;
  numOfRows: number;
  pageNo: number;
};

type Item = {
  item: [];
};

type fetchData = {
  std_year: string;
  sido_sgg_nm: string;
  acc_cl_nm: string;
  acc_cnt: string;
  acc_cnt_cmrt: string;
  dth_dnv_cnt: string;
  dth_dnv_cnt_cmrt: string;
  ftlt_rate: string;
  injpsn_cnt: string;
  injpsn_cnt_cmrt: string;
  tot_acc_cnt: string;
  tot_dth_dnv_cnt: string;
  tot_injpsn_cnt: string;
};
const AboutPage = ({ val }: InferGetStaticPropsType<typeof getServerSideProps>) => {
  return (
    <div className={styles.container}>
      {val.map((item: fetchData, index) => {
        return <div key={index}>{item.std_year}</div>;
      })}
    </div>
  );
};

export async function getServerSideProps() {
  const res = await fetch(
    `http://apis.data.go.kr/B552061/lgStat/getRestLgStat?ServiceKey=1%2FEJ91e6fCvfjNcZwzIFg2MPmpgqoBzdVaySj2RcGHIQQhjC3zMYLETRi1EtZZt6mDeVr%2F1MnM0PcIkelqZEDA%3D%3D&searchYearCd=2019&siDo=1100&guGun=1116&numOfRows=13&pageNo=1&type=json`,
  );

  const data: Data = await res.json();

  const val: [] = data.items.item;
  console.log(data.items.item);

  return { props: { val } };
}

export default AboutPage;
