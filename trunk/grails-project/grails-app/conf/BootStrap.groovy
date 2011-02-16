import com.teambook.model.*

class BootStrap {

    def init = { servletContext ->
            def football = new Discipline(name: "Futbol 5", playersPerTeam: 5);
            def singlesTennis = new Discipline(name: "Tenis singles", playersPerTeam: 1)
            def doublesTennis = new Discipline(name: "Tenis dobles", playersPerTeam: 2)
            def disciplines = [football, singlesTennis, doublesTennis]
            disciplines.each { it.save(failOnError: true) }

            def users = []
            users.add(new User(
                    username: 'user1',
                    name: 'John Doe',
                    birthday: Date.parse('d-M-yyyy', '20-7-1975'),
                    email: 'johndoe@email.com'))
            users.add(new User(
                    username: 'user2',
                    name: 'Paul Smith',
                    birthday: Date.parse('d-M-yyyy', '12-4-1983'),
                    email: 'psmith@mymail.com'))
            users.add(new User(
                    username: 'user3',
                    name: 'Martin Palermo',
                    birthday: Date.parse('d-M-yyyy', '7-11-1973'),
                    email: 'mpalermo@bocajrs.com.ar'
            ))
            users.each { it.save(failOnError: true) }

            new PlayingField(
                    name: 'Cancha de futbol 5',
                    address: 'Suiza 1840',
                    availableDisciplines: [football]).save(failOnError: true)
            def tennisField = new PlayingField(
                    name: 'Club de tenis',
                    address: 'Jose Hernandez 2930 entre Martin Fierro y Sgto. Cruz',
                    availableDisciplines: [singlesTennis, doublesTennis])
            tennisField.save(failOnError: true)

            def teams = []
            teams.add new Team(
                    name: 'John Federer',
                    players: [users[0]],
                    discipline: singlesTennis)
            teams.add new Team(
                    name: 'Paul',
                    players: [users[1]],
                    discipline: singlesTennis)
            teams.add new Team(
                    name: 'Boca Junios',
                    players: [users[2]],
                    discipline: football)
            teams.each { it.save(failOnError: true) }

            def maniana = Calendar.instance
            maniana.add(Calendar.DAY_OF_YEAR, 1)
            def manianaYUnaHora = Calendar.instance
            manianaYUnaHora.add(Calendar.DAY_OF_YEAR, 1)
            manianaYUnaHora.add(Calendar.HOUR, 1)

            new Match(
                    name: "Tenis el sabado",
                    startingTime: maniana.time,
                    endingTime: manianaYUnaHora.time,
                    publicMatch: true,
                    owner: users[0],
                    localTeam: teams[0],
                    awayTeam: teams[1],
                    field: tennisField,
                    discipline: teams[0].discipline ).save(failOnError: true)
            new Match(
                    name: "Mano a mano",
                    startingTime: maniana.time,
                    endingTime: manianaYUnaHora.time,
                    publicMatch: true,
                    owner: users[0],
                    localTeam: teams[1],
                    awayTeam: teams[0],
                    field: tennisField,
                    discipline: teams[0].discipline ).save(failOnError: true)
            new Match(
                    name: "Fulbacho",
                    startingTime: maniana.time,
                    endingTime: manianaYUnaHora.time,
                    publicMatch: true,
                    owner: users[1],
                    localTeam: teams[2],
                    awayTeam: teams[1],
                    field: tennisField,
                    discipline: teams[2].discipline ).save(failOnError: true)
    }
    def destroy = {
    }
}
