import Link from "next/link";

interface NavbarProps{
    active:"Dashboard"| "Treinos"| "Check-in" | "Login"
}

export default function Navbar (props:NavbarProps){
    const{active}=props
    const activeClass="border-b-2 border-green-600 pb-4"

const link=[
    {link:"/dashboard", label: "Dashboard"},
    {link:"/workouts", label: "Treinos"},
    {link:"/checkin", label: "Check-in"},
    {link:"/login", label: "Login"}
]

    return(
        <nav className="flex px-6 pt-6  justify-between bg-slate-900">
        <h1 className="text-4xl font-bold">Gyma</h1>

        <ul className="flex gap-6 text-xl ">
            {link.map(link=>
                    <li key={link.label} className={link.label=== active? activeClass: ""}><Link href={link.link}>{link.label}</Link></li>
        
            )}
        </ul>
        <img className="size-12 rounded-full" src="https://www.arlindovsky.net/wp-content/uploads/2023/05/sapo-e1683237806251.png" alt="" />
    </nav>
    )
}

