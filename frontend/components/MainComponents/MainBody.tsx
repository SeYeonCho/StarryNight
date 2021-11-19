import React, { useState } from 'react';
import { AiOutlineQuestionCircle, AiOutlineSearch } from 'react-icons/ai';
// import useSWR from 'swr';
import styles from '../../styles/MainBody.module.scss';
import useSWRImmutable from 'swr/immutable'
import { useRouter } from 'next/router';
import MainGraph from './MainGraph';
import { GiRank3 } from "react-icons/gi";
import { FaFileWord } from "react-icons/fa";
import ReactHover, { Trigger, Hover } from "react-hover";
import KeyWordManual from './KeyWordManual';

const fetcher = url => fetch(url, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    },
  }).then(async res => {
    //console.log(res.json())
    let data = await res.json();
    return data;
  })

  const optionsCursorTrueWithMargin = {
    followCursor: true,
    shiftX: 20,
    shiftY: -25
  };

const MainBody = () => {

    // const { data, error } = useSWRImmutable(`http://localhost:3000/mention`, fetcher);
    const { data, error } = useSWRImmutable(`https://j5b103.p.ssafy.io/api/word/trend`, fetcher);
 
    const textInput = React.useRef<any>();
    const router = useRouter();

    const submitInput = () => {
        router.push({
            pathname: '/search',
            query: { word: textInput.current.value.replace(/ /g,"").trim()}
        });
    }
    let datas: null | any[] = null;

    if (data) {
        datas = data?.keywords;
    }
    
    const goEnter = (e) => {
        if (e.key === 'Enter') {
            submitInput();
        }
    }

    const getNews = (e,event) => {
        
        let target = event.target.innerText.replace(/ /g, "");
        if (target.length === 5 && target.includes('>')) {
            window.open(e.news_url, e.news_title,
                "resizable,scrollbars,status"
            );
        }
        else {
            router.push({
                pathname: '/search',
                query: { word: e.title.replace(/ /g,"").trim()}
            });
            
        }

    }

    return (
        <>
            <div id={styles.searchBox}>
                <input id={styles.searchInput} ref={textInput} maxLength={10}
                    placeholder="이 곳을 눌러 키워드 분석을 시작해보세요."
                    onKeyPress={goEnter}
                ></input>
                <AiOutlineSearch id={styles.inputInsideIcon} onClick={submitInput}/>
            </div>

            <div id={styles.keyWordList}>

                <div id={styles.keyWordTitle}>
                    인기 검색어 Top 20
                    <ReactHover options={optionsCursorTrueWithMargin}>
                        <Trigger type="trigger">
                            <AiOutlineQuestionCircle className={styles.questionIcon1}/>
                        </Trigger>
                        <Hover type="hover" className={styles.hoverBox}>
                            <KeyWordManual />
                        </Hover>
                    </ReactHover>
                </div>
                <div id={styles.keyWordTopList}>
                    
                { datas && datas.map((e,index) => {
                    return <div key={index} className={styles.dataKeyword}
                        onClick={(event)=>getNews(e, event)}
                    >
                        <div className={styles.dataKeyWordTitle}># {`${e.title}`}</div>
                        <div className={styles.dataKeyWordLink}                            
                        >관련 기사 &#62;</div>                        
                    </div>
                })}
                </div>
            </div>
        </>
    );
}

export default MainBody;