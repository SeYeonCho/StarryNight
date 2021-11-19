import dynamic from 'next/dynamic';
import LoadingComponent from '../components/LoadingComponent/LoadingComponent';

const DynamicComponent = dynamic(() =>
import('../components/IndexPageComponent/IndexPage'), {
loading: function loadingComponent() {
  return <LoadingComponent />;
},
});

const Index = () => {
  
  return (
    <>
      <DynamicComponent />
    </>
  );
}

export default Index;