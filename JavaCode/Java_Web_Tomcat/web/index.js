let doDelCust = (id) => {
    if(confirm("是否確認刪除?")){
        window.location.href=`/customer.do?id=${id}&operate=delete`
    }
}

let goPage =(pageNo)=>{
    window.location.href=`/customer.do?pageNo=${pageNo}`
}