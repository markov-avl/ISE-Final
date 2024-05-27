import React, { Dispatch, SetStateAction, useEffect, useState } from 'react'
import Filters from '@/shared/Filters/Filters'
import { DataCustomNameByField } from '@/types/DataCustomNameByField'
import { FilterObject } from '@/components/VideoGamesTable/VideoGames'
import { get } from '@/api/baseApi'

interface VideoGamesFiltersProps {
	setQuery: Dispatch<SetStateAction<QueryModel>>
}

const VideoGamesFilters = ({ setQuery }: VideoGamesFiltersProps) => {
	useEffect(() => {
		getGenres().then(data => setGenres(data.map(({ name }) => name)))
		getPlatforms().then(data => setPlatforms(data.map(({ name }) => name)))
		getPublishers().then(data => setPublishers(data.map(({ name }) => name)))
		getYears().then(data => setYears(data))
		getRegions().then(data => setRegions(data))
	}, [])

	const getGenres = async (): Promise<GenreModel[]> => {
		return await get('genres', '').then(data => data.data)
	}

	const getPlatforms = async (): Promise<PlatformModel[]> => {
		return await get('platforms', '').then(data => data.data)
	}

	const getPublishers = async (): Promise<PublisherModel[]> => {
		return await get('publishers', '').then(data => data.data)
	}

	const getYears = async (): Promise<number[]> => {
		return await get('released-games/years', '').then(data => data.data)
	}

	const getRegions = async (): Promise<string[]> => {
		return await get('released-games/regions', '').then(data => data.data)
	}

	const [genres, setGenres] = useState<string[]>([])
	const [publishers, setPublishers] = useState<string[]>([])
	const [platforms, setPlatforms] = useState<string[]>([])
	const [years, setYears] = useState<number[]>([])
	const [regions, setRegions] = useState<string[]>([])

	const [selectedFilters, setSelectedFilters] = useState<FilterObject<Game>>({})

	useEffect(() => {
		setQuery(prevQuery => ({
			...prevQuery,
			publishers: selectedFilters.publisher || [],
			genres: selectedFilters.genre || [],
			platforms: selectedFilters.platform || [],
			years: selectedFilters.year || [],
			regions: selectedFilters.region || []
		}))
	}, [selectedFilters])

	const filters: DataCustomNameByField[] = [
		{
			key: 'publisher',
			name: 'Фильтр по Издателю',
			data: publishers
		},
		{
			key: 'platform',
			name: 'Фильтр по Платформе',
			data: platforms
		},
		{
			key: 'genre',
			name: 'Фильтр по Жанру',
			data: genres
		},
		{
			key: 'year',
			name: 'Фильтр по Году',
			data: years
		},
		{
			key: 'region',
			name: 'Фильтр по региону',
			data: regions
		}
	]

	return (
		<div style={{ display: 'flex' }}>
			<Filters
				selectedFilters={selectedFilters}
				filters={filters}
				setSelectedFilters={setSelectedFilters}
			/>
		</div>
	)
}

export default VideoGamesFilters
