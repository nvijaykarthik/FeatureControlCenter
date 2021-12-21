export const SERVICE_DOMAIN=process.env.REACT_APP_SERVICE_DOMAIN

export const Spinner=()=>{
    return( <div id="overlay" onclick="off()">
    <div class="lds-ring"><div></div><div></div><div></div><div></div></div>
    </div>)
}