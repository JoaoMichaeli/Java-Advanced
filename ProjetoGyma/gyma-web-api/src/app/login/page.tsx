import Navbar from "@/components/navbar";

export default function LoginPage(){

    return(
        <>
        <Navbar active="Login"/>

        <main className="flex items-center justify-center">
            <div className=" bg-slate-900 rounded min-w-2/3 p-5 m-6">
                <h2 className="text-lg font-bold text-white ">Check-In</h2>
            </div>
        </main>
        </>
    )
}