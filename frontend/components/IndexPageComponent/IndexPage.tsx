import type { NextPage } from "next";
import React, { useEffect, useState, useRef } from "react";
import styles from "../../styles/Index.module.scss";
import ScrollDownComponent from "./ScrollDownComponent";

import dynamic from 'next/dynamic';
import LoadingComponent from "../LoadingComponent/LoadingComponent";

const MainPage = dynamic(() =>
import('../MainComponents/MainPage'), {
loading: function loadingComponent() {
  return <LoadingComponent />;
},
});

const IndexPage: NextPage = () => {
  const mainText = useRef<any>();
  const circleBackground = useRef<any>();

  const [isLastScroll, setIsLastScroll] = useState(false);
  const [isScrollTop, setIsScrollTop] = useState(true);
  useEffect(() => {
    window.scrollTo(0,0);
    function handleScroll() {
      const scrollTop = window.scrollY;
      let innerHeight = window.innerHeight;
      const isEndOfPage = (innerHeight + window.scrollY) >= document.body.offsetHeight;
      let point = innerHeight / 2;
     
      circleBackground.current.style.borderRadius
        = point > scrollTop ? '50%' : '0%';
      if (isEndOfPage) {
        mainText.current.style.color = "#FFD523";
        setIsLastScroll(true);
      }
      else {
        
        mainText.current.style.color = "white";
        setIsLastScroll(false);
      }

      scrollTop === 0 ? setIsScrollTop(true) : setIsScrollTop(false);
      
    }

    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <div>
      <article className={styles.articleTarget}>
        <div id={styles.stars}></div>
        <section id={styles.mainText} ref={mainText}>
          <h1>
          저희가
          <br></br>
          무엇을 할 수 있는지
          <br></br>
          보여드릴게요
          </h1>
          {
          isScrollTop ?
              <ScrollDownComponent /> :
            <></>
        }
        </section>
        
        <section ref={circleBackground} id={styles.firstSection}>
          {isLastScroll ? <MainPage /> : <></>}
        </section>
      </article>
    </div>
  );
};

export default IndexPage;
