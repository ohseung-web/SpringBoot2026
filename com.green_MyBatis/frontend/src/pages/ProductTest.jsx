import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './Member.css';

export default function ProductTest(){

    // 모든 값을 하나의 객체로 관리하기
    const [test, setTest] = useState({
        a_1:'',
        a_2:'',
        a_3:'',
        a_4:'',
        a_5:'',
        a_6:'',
        a_7:'',
        a_8:'',
        a_9:'',
        a_10:'',
        a_11:'',
        a_12:'',
        a_13:'',
        a_14:'',
        a_15:'',
        a_16:'',
        a_17:'',
        a_18:'',
        a_19:'',
        a_img:null,
    });
    
    const navigate = useNavigate();

    // 상품등록 submit 함수
    const handleSubmit = () =>{

        // React에서 이미지 업로드시 반드시 formData 객체를 생성한다.
        const formData = new FormData();

        /// 파일만 별도로 추가
    formData.append('testFile', test.a_img);

    // 나머지 텍스트 필드들을 JSON 하나로 묶어서 추가
    const textData = {
        a_1: test.a_1,
        a_2: test.a_2,
        a_3: test.a_3,
        a_4: test.a_4,
        a_5: test.a_5,
        a_6: test.a_6,
        a_7: test.a_7,
        a_8: test.a_8,
        a_9: test.a_9,
        a_10: test.a_10,
        a_11: test.a_11,
        a_12: test.a_12,
        a_13: test.a_13,
        a_14: test.a_14,
        a_15: test.a_15,
        a_16: test.a_16,
        a_17: test.a_17,
        a_18: test.a_18,
        a_19: test.a_19
    };

    // 1번: 전송 전 textData 객체 확인
    console.log("전송할 textData 객체:", textData);

    // 2번: JSON 문자열로 변환된 모습 확인
    console.log("JSON.stringify 결과:", JSON.stringify(textData));

    // 3번: 파일 확인
    console.log("전송할 파일:", test.a_img);

    // JSON 문자열로 변환해서 testData 하나로 묶기
    formData.append('testData', JSON.stringify(textData));

        axios.post("/api/test/insert",formData)
        .then((res)=>{
            if(res.data === 1){
                alert("상품등록 성공");
                navigate("/");
            }
        })
        .catch((error)=>{
            console.log(error);
            alert("등록 실패");
        })
    }

     // 공통 입력 처리 함수
     const handleChange = (e) =>{
        // input의 name 값을 가져오기
        const inputName = e.target.name;
        if(e.target.type === 'file'){
            // ...car를 반드시 얕은 복사해야 함
            // 얕은 복사 하지 않으면 랜더링이 안됨
            // 스프레드구문 펼처진상태로 원하는 값 삽입
            setTest({...test, [inputName]:e.target.files[0]});
        }else{
            // file를 제외한 모든 숫자, 문자,의 input value 저장
            setTest({...test, [inputName]:e.target.value});
        }

     }

    return (
    <div id="section_wrap">
      <div className="word">상품등록</div>
      <table width="500" border="1">
        <tbody>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_1" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_2" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_3" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_4" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_5" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_6" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_7" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_8" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_9" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_10" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_11" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_12" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_13" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_14" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_15" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_16" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_17" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_18" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>테스트1</td>
            <td>
              <input type="text" name="a_19" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>자동차 이미지</td>
            <td>
              <input type="file" name="a_img" onChange={handleChange} />
            </td>
          </tr>
          
          <tr>
            <td colSpan="2" align="center">
              <button type="button" onClick={handleSubmit}>
                상품등록
              </button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );


}