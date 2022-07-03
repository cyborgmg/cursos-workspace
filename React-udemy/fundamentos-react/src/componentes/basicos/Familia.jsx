import React from 'react'


export default props => {

    return (
        <div>
            {/*             

            <FamiliaMembro nome="Pedro" sobrenome={props.sobrenome}/>
            <FamiliaMembro nome="Maria" {...props}/>
            <FamiliaMembro nome="Joana" sobrenome="silva"/>
        
            */}

            {/* {React.cloneElement(props.children, {...props})} */}

            {/*             
            { 
                props.children.map((child,i) => {
                    return React.cloneElement(child, {...props,i})
                })
                    
            } 
            */}

            { 
                props.children.map(child => {
                    return React.cloneElement(child, props)
                })
                    
            }
            
        </div>
    )

}