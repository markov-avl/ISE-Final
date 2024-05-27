import React, { useEffect, useState } from 'react'
import Sorters, { ISorters } from '@/shared/Sorters/Sorters'
import Chart from '@/shared/Chart/Chart'
import ChartSelectors from '@/shared/Chart/ChartSelectors'
import VideoGamesFilters from '@/components/VideoGamesFilters/VideoGamesFilters'
import VideoGamesPaginationTable from '@/components/VideoGamesPaginationTable/VideoGamesPaginationTable'
import { get } from '@/api/baseApi'

export type FilterObject<Type> = {
	[key in keyof Type]?: string[]
}

const VideoGames = () => {
	const [displayedData, setDisplayedData] = useState<Game[]>()
	const [sorters, setSorters] = useState<ISorters<Game, keyof Game>[]>([
		{
			key: 'year',
			name: 'Сортировка по Году',
			sorting: 'asc'
		},
		{
			key: 'genre',
			name: 'Сортировка по Жанру',
			sorting: 'asc'
		},
		{
			key: 'platform',
			name: 'Сортировка по Платформе',
			sorting: 'asc'
		}
	])
	const [showOnChart, setShowOnChart] = useState<string>('genre')
	const [aggregator, setAggregator] = useState<string>('sum')

	const [query, setQuery] = useState<QueryModel>({
		page: 1,
		size: 15,
		publishers: [],
		platforms: [],
		genres: [],
		years: [],
		regions: [],
		sort: ['asc-year', 'asc-genre', 'asc-platform']
	})

	useEffect(() => {
		const selectedSorts = []
		sorters.forEach(({ key, sorting }) => {
			if (sorting !== 'ns') selectedSorts.push(sorting + '-' + key)
		})
		setQuery(prevQuery => ({
			...prevQuery,
			sort: selectedSorts
		}))
	}, [sorters])

	useEffect(() => {
		get(
			'sales/chart/' + showOnChart,
			new URLSearchParams({ ...query, size: 200000 }).toString() +
			'&aggregator=' +
			aggregator
		).then(data => {
			setDisplayedData(data.data)
		)
		}, [query, aggregator, showOnChart])

		return (
			<>
				<div style={{ display: 'flex' }}>
					<VideoGamesFilters setQuery={setQuery} />
					<Sorters sorters={sorters} setSorters={setSorters} />
					<ChartSelectors
						onClickShowOnChart={e => {
							setShowOnChart(e.target.value)
						}}
						onClickAggregation={e => {
							setAggregator(e.target.value)
						}}
					/>
				</div>
				<Chart
					data={displayedData}
					showOnChart={showOnChart}
					aggregator={aggregator}
				/>
				<VideoGamesPaginationTable
					setCurrentPage={(p: number) =>
						setQuery(prevQuery => ({ ...prevQuery, page: prevQuery.page + p }))
					}
					query={query}
				/>
			</>
		)
	}

	export default VideoGames
