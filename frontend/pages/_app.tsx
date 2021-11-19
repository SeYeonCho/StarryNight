import '../styles/globals.css'
import '../styles/globals.scss'
import type { AppProps } from 'next/app'
import Head from 'next/head'
import React from 'react'
import { NextSeo } from 'next-seo'

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <title>별나린밤</title>
          <link rel='icon' href="/favicon.png">
        </link>
        <meta name="keywords" content="search, 검색, 별나린밤, SSAFY, click" />
        <meta name="description" content="별나린밤 - 키워드 트렌드를 파악하기 위한 검색(search, click) 사이트!"></meta>
        <link rel="canonical" href="https://j5b103.p.ssafy.io/" />
        <meta name="naver-site-verification" content="f0b6fdad2179cdccab05ecf27abb7d9644755d35" />
        <meta httpEquiv="Content-Security-Policy" content="upgrade-insecure-requests" />
      <meta name="google-site-verification" content="KvlcKlucdM-Fvv2b8dOcmUWynCBXhySnSl2l2qK0zao" />  
      </Head>
      <NextSeo
        title="별나린밤"
        description="별나린밤 - 키워드 트렌드를 파악하기 위한 검색(search,click) 사이트!"
      />
      <Component {...pageProps} />
    
    </>
  )
}
export default MyApp
