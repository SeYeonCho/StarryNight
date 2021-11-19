import React, {useState} from 'react';
import { GetServerSideProps } from 'next';
import dynamic from 'next/dynamic';
import {useRouter} from 'next/router';
import LoadingComponent from '../components/LoadingComponent/LoadingComponent';

const DynamicComponent = dynamic(() =>
  import('../components/SearchComponents/SearchPage'), {
  loading: function loadingComponent() {
    return <LoadingComponent />;
  },
});

function Search() {
  return (
    <div>
      <DynamicComponent />
    </div>
  );
}

export const getServerSideProps: GetServerSideProps = async (context) => {
  return {
    props: {
      data: []
    }
  }
};

export default Search;
