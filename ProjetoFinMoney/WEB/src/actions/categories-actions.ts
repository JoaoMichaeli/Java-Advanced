export async function getCategories(){
    const response = await fetch("http://localhost:8080/categories")
    return await response.json()
}